#! /bin/bash

docker build -t planetek/ubuntu-maven-git:base -f Dockerfile.base .
docker build --no-cache -t planetek/eosai_esb:$1 --build-arg branch=$1 .
