#!/bin/bash
VERSION=`cat build.gradle | grep "version = \"" | awk '{gsub("version = ", "") ; gsub("\"","") ; print}'`
echo "Releasing v${VERSION} of JPower"
echo "Tagging Release"
git tag v${VERSION} -m "JPower v${VERSION}" -f
echo "Pushing Changes"
git push origin master
echo "Pushing Tags"
git push --tags origin
echo "Uploading to Sonatype"
gradle upload
