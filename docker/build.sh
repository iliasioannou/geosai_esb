#! /bin/bash

docker build -t dockerhub.planetek.it/ubuntu-maven-git-muleesb:base -f Dockerfile.base .
docker push dockerhub.planetek.it/ubuntu-maven-git-muleesb:base

docker build -t dockerhub.planetek.it/pkh111_eosai_esb:base -f Dockerfile.base.code .
docker push dockerhub.planetek.it/pkh111_eosai_esb:base

docker build --no-cache -t dockerhub.planetek.it/pkh111_eosai_esb:$1 --build-arg branch=$1 .
docker push dockerhub.planetek.it/pkh111_eosai_esb:$1
