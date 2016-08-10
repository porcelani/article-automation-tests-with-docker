#!/usr/bin/env bash
docker run -it --rm --name site-maven-container -v "$PWD":/usr/src/mymaven -w /usr/src/mymaven maven:3-jdk-8 mvn clean install