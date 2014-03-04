package jpower.gradle

import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPom
import org.gradle.api.publish.maven.MavenPublication

class JPower {

    static Project project

    static MavenPom pom(MavenPom pom) {
        return JPowerPom.setup(project, pom)
    }

    static void setup() {
        def versionInfo = project.rootProject.ext.versionInfo = new Version(project.rootProject.version as String)
        def sonatypeUsername = project.hasProperty("sonatypeUsername") ? project.property("sonatypeUsername") : ""
        def sonatypePassword = project.hasProperty("sonatypePassword") ? project.property("sonatypePassword") : ""

        project.apply plugin: "maven-publish"

        def pubs = project.extensions.getByType(PublishingExtension) as PublishingExtension

        pubs.publications {
            all(MavenPublication) {
                from(project.components.getByName("java"))
                groupId project.rootProject.group
                artifactId "jpower-${project.name.toLowerCase()}"
                version versionInfo.maven
                pom(delegate.pom)
            }
        }

        pubs.repositories {
            maven {
                name = "sonatype"
                url = "https://oss.sonatype.org/content/repositories/snapshots/"
                credentials {
                    username = sonatypeUsername
                    password = sonatypePassword
                }
            }
        }
    }
}
