package jpower.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class ReleaseTask extends DefaultTask {
    boolean tagRelease = false
    boolean pushRelease = false
    boolean rebaseMaster = false

    ReleaseTask() {
    }

    @TaskAction
    void release() {
        def nextVersion = new Version(project.version as String).increment().toString()

        /* Git Preparation Stuff */
        ({
            project.exec {
                commandLine "git", "pull", "--all"
            }
        })()

        /* Actually Release */
        ({
            if (pushRelease)
            {
                logger.lifecycle "Pushing to Git Repository"
                project.exec {
                    commandLine "git", "push", "origin", "develop"
                }
            }

            if (tagRelease)
            {
                logger.lifecycle "Creating Tag v${project.version}"
                project.exec {
                    commandLine "git", "tag", "v${project.version}"
                }
            }

            project.exec {
                commandLine "git", "checkout", "-b", "origin/master"
                ignoreExitValue = true
            }

            project.exec {
                commandLine "git", "checkout", "develop"
                ignoreExitValue = true
            }

            if (rebaseMaster)
            {
                logger.lifecycle "Updating Master Branch"
                project.exec {
                    commandLine "git", "checkout", "master"
                }
                project.exec {
                    commandLine "git", "rebase", "v${nextVersion}"
                }
                if (pushRelease)
                {
                    project.exec {
                        commandLine "git", "push", "origin", "master"
                    }
                }
                project.exec {
                    commandLine "git", "checkout", "develop"
                }
            }

            if (pushRelease)
            {
                logger.lifecycle "Pushing Tags to Git Repository"
                project.exec {
                    commandLine "git", "push", "--tags"
                }
            }
        })()

        /* Update to new version on develop branch */
        ({
            def propFile = project.file("gradle.properties")
            propFile.text = propFile.text.replace("jpower.version=${project.version}", "jpower.version=${nextVersion}")
            project.exec {
                commandLine "git", "add", "gradle.properties"
            }
            project.exec {
                commandLine "git", "commit", "-m", "Update Version to v${nextVersion}"
            }
            project.exec {
                commandLine "git", "push", "origin", "develop"
            }
        })()
    }

    void git(Map<String, Boolean> options) {
        tagRelease = options["tag"] as boolean
        pushRelease = options["push"] as boolean
        rebaseMaster = options["updateMaster"] as boolean
    }
}
