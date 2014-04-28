package jpower.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class ReleaseTask extends DefaultTask {
    boolean tagRelease = false
    boolean pushRelease = false

    ReleaseTask() {
    }

    @TaskAction
    void release() {
        if (pushRelease) {
            logger.lifecycle "Pushing to Git Repository"
            project.exec {
                commandLine "git", "push"
            }
        }
        if (tagRelease) {
            logger.lifecycle "Creating Tag v${project.version}"
            project.exec {
                commandLine "git", "tag", "v${project.version}"
            }
        }
        def nextVersion = new Version(project.version as String).increment().toString()
        def propFile = project.file("gradle.properties")
        propFile.text = propFile.text.replace("jpower.version=${project.version}", "jpower.version=${nextVersion}")
        project.exec {
            commandLine "git", "add", "gradle.properties"
        }
        project.exec {
            commandLine "git", "commit", "-m", "Update Version to v${nextVersion}"
        }
        if (pushRelease) {
            logger.lifecycle "Pushing Tags to Git Repository"
            project.exec {
                commandLine "git", "push", "--tags"
            }
        }
    }

    void git(Map<String, Boolean> options) {
        tagRelease = options["tag"] as boolean
        pushRelease = options["push"] as boolean
    }
}
