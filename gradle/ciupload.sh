#!/bin/bash

# Clone Build Storage
git clone --depth 1 https://${UPLOAD_USERNAME}:${UPLOAD_PASSWORD}@github.com/DirectMyFile/build.git .tmp/

ORIGDIR=${PWD}

# Copy JPower Build Data
./.tmp/copy.sh JPower ${PWD}/build/

cd .tmp/

rm -rf JPower/tmp

git add .; git ls-files --deleted | xargs --no-run-if-empty git rm
git commit -m "Update JPower Results"
git push -u origin gh-pages
cd ${ORIGDIR}
rm -rf .tmp/
