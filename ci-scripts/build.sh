#!/bin/bash

set -ex

USERNAME=bilalcaliskan
EMAIL=bilalccaliskan@gmail.com
PROJECT=postgres-groovy-demo
RELEASE_VERSION=`grep RELEASE_VERSION version.properties | cut -d "=" -f2`
CLONE_URL=git@gitlab.com:vpnbeast/backend/${PROJECT}.git
BRANCH=master


function checkIfVersionExists() {
  # does not work for now because it exit from that script and run another script. Docker job must be done in that same script.
  if git show-ref --tags | egrep -q "refs/tags/${RELEASE_VERSION}.RELEASE"
  then
    echo "Already released this version!"
    # you should exit with return code 1 to fail job
    exit 1
  fi

}

function gitGlobalSettings() {
  git config user.userName ${USERNAME}
  git config user.email ${EMAIL}
}

function gitCommitAndPush() {
  git --no-pager diff
  git add -A
  git commit -am "[skip ci] version ${RELEASE_VERSION}.RELEASE"
  git tag -a "${RELEASE_VERSION}.RELEASE" -m "v${RELEASE_VERSION} tagged"
  # git tag -a "$RELEASE_VERSION" -m "v$RELEASE_VERSION tagged"
  git status
  git push --follow-tags http://${USERNAME}:${GIT_ACCESS_TOKEN}@gitlab.com/${USERNAME}/${PROJECT}.git HEAD:${BRANCH}
}

function mvnInstallAndPackage() {
  git checkout ${BRANCH}
  git pull
  mvn clean install
  mvn clean package
  mvn versions:set -DnewVersion=${RELEASE_VERSION}
  mvn versions:commit -B
  # use below command if you want to release build artifacts to remote repository
  # mvn clean package deploy:deploy -B
}


echo "version: ${RELEASE_VERSION}"
checkIfVersionExists
mvnInstallAndPackage
gitGlobalSettings
gitCommitAndPush
