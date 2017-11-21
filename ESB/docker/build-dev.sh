#! /bin/bash

docker build -t planetek/ubuntu-maven-git:base -f Dockerfile.base .
docker build -t planetek/eosai_esb:dev -f Dockerfile.dev .
