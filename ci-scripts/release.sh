#!/bin/bash

set -ex

IMAGE=postgres-groovy-demo
RELEASE_VERSION=`grep RELEASE_VERSION version.properties | cut -d "=" -f2`
BRANCH=master

echo "version: $RELEASE_VERSION"
git stash && git pull origin ${BRANCH}
docker login -u ${DOCKER_USERNAME} -p ${DOCKER_PASSWORD}
echo "building docker image with maven..."
mvn install dockerfile:build
echo "tagging builded image with the version info..."
docker tag ${DOCKER_USERNAME}/${IMAGE}:latest ${DOCKER_USERNAME}/${IMAGE}:${RELEASE_VERSION}
echo "pushing with version info..."
docker push ${DOCKER_USERNAME}/${IMAGE}:${RELEASE_VERSION}
echo "pushing with latest tag..."
docker push ${DOCKER_USERNAME}/${IMAGE}:latest
