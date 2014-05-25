#!/bin/bash
git clone https://${UPLOAD_USERNAME}:${UPLOAD_PASSWORD}@github.com/DirectMyFile/tests.git .tmp/
ORIGDIR=${PWD}
cd .tmp/
./copy.sh JPower ${PWD}/build/reports/allTests/
git add .; git ls-files --deleted | xargs --no-run-if-empty git rm
git commit -m "Update JPower Tests"
git push -u origin gh-pages
cd ${ORIGDIR}
rm -rf .tmp/
