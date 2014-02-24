package jpower.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class ReleaseTask extends DefaultTask {
    boolean tagRelease = false
    boolean pushRelease = false

    ReleaseTask() {
    }

    @TaskAction
    void tagRelease() {
        if (tagRelease) {
            logger.lifecycle "Creating Tag v${project.version}"
            project.exec {
                commandLine "git", "tag", "v${project.version}"
            }
        }
    }

    @TaskAction
    void pushRelease() {
        if (pushRelease) {
            logger.lifecycle "Pushing to Git Repository"
            project.exec {
                commandLine "git", "push"
            }
        }
    }

    void git(Map<String, Boolean> options) {
        tagRelease = options["tag"] as boolean
        pushRelease = options["push"] as boolean
    }
}
