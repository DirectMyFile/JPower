package jpower.gradle

import org.gradle.api.Action
import org.gradle.api.XmlProvider
import org.gradle.api.publish.maven.MavenPom

class JPowerPom {
    static void setup(MavenPom pom)
    {
        pom.withXml(generate())
    }

    static Action<XmlProvider> generate() {
        new Action<XmlProvider>() {
            @Override
            void execute(XmlProvider provider) {
                def xml = provider.asNode()
                xml.with { node ->
                    appendNode("url", "http://www.directmyfile.com/")

                    appendNode("licenses").with {
                        appendNode("license").with {
                            appendNode("name", "GNU Lesser General Public License (LGPL), Version 3")
                            appendNode("url", "http://www.gnu.org/licenses/lgpl.txt")
                            appendNode("distribution", "repo")
                        }
                    }

                    appendNode("scm").with {
                        appendNode("connection", "scm:git:git://github.com/DirectMyFile/JPower.git")
                        appendNode("developerConnection", "scm:git:git@github.com:DirectMyFile/JPower.git")
                        appendNode("url", "https://github.com/DirectMyFile/JPower/")
                    }
                    
                    appendNode("organization").with {
                        appendNode("name", "DirectCode")
                        appendNode("url", "http://directmyfile.github.io/")
                    }
                }
            }
        }
    }
}
