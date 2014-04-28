package jpower.gradle

import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPom
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.api.tasks.bundling.Jar
import org.gradle.api.tasks.javadoc.Javadoc

class JPower
{
    static Project project

    static void pom(MavenPom pom)
    {
        JPowerPom.setup(pom)
    }

    static void setup()
    {
        def versionInfo = project.rootProject.ext.versionInfo = new Version(project.rootProject.version as String)
        def sonatypeUsername = project.hasProperty("sonatypeUsername") ? project.property("sonatypeUsername") : ""
        def sonatypePassword = project.hasProperty("sonatypePassword") ? project.property("sonatypePassword") : ""

        def sourceJar = project.tasks.create("sourcesJar", Jar) as Jar

        sourceJar.dependsOn("classes")

        def javadocJar = project.tasks.create("javadocJar", Jar) as Jar

        javadocJar.dependsOn("javadoc")

        sourceJar.with {
            classifier = "sources"
            from((project.sourceSets as SourceSetContainer).getByName("main").allSource)
        }

        javadocJar.with {
            classifier = "javadoc"
            from((project.tasks.getByName("javadoc") as Javadoc).destinationDir)
        }

        project.apply plugin: "maven-publish"

        def pubs = project.extensions.getByType(PublishingExtension) as PublishingExtension

        pubs.publications {
            all(MavenPublication) {
                from(project.components.getByName("java"))
                groupId project.rootProject.group
                artifactId "jpower-${project.name.toLowerCase()}"
                version versionInfo.maven
                pom(delegate.pom)
                artifact sourceJar
                artifact javadocJar
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
