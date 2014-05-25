#!/bin/bash

# Clone Build Storage
git clone https://${UPLOAD_USERNAME}:${UPLOAD_PASSWORD}@github.com/DirectMyFile/build.git .tmp/

ORIGDIR=${PWD}

# Copy JavaDoc to the Storage
./.tmp/copy.sh JPower/javadoc/ ${PWD}/build/javadoc/

# Copy Test Results to the Storage
./.tmp/copy.sh JPower/tests/ ${PWD}/build/reports/allTests/

cd .tmp/
git add .; git ls-files --deleted | xargs --no-run-if-empty git rm
git commit -m "Update JPower Results"
git push -u origin gh-pages
cd ${ORIGDIR}
rm -rf .tmp/
