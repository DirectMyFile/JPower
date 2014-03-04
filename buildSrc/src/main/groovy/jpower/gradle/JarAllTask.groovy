package jpower.gradle

import org.gradle.api.tasks.bundling.Jar

class JarAllTask extends Jar {
    JarAllTask() {
        destinationDir = project.file("${project.buildDir}/libs/")
        classifier = "${project.name}-${project.version}-all"
        project.childProjects.values().each { child ->
            dependsOn.add(child.tasks.getByName("classes"))
            dependsOn.add(child.tasks.getByName("processResources"))
            from "${child.buildDir}/classes/main"
            from "${child.buildDir}/resources/main"
        }
    }
}
