#! /bin/bash

docker build --no-cache -t planetek/cmems_processors:$1 --build-arg branch=$1 .
