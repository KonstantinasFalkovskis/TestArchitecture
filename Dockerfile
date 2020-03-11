FROM openjdk:8-jdk-alpine3.9
#FROM openjdk:8u191-jre-alpine3.8

RUN apk add curl jq

# Workspace
WORKDIR /usr/share/tests

# ADD .jar under target from host
# into this image
ADD target/selenium-docker.jar 			selenium-docker.jar
ADD target/selenium-docker-sources.jar 	selenium-docker-sources.jar
ADD target/selenium-docker-tests.jar 	selenium-docker-tests.jar
ADD target/libs						    libs

# in case of any other dependency like .csv / .json / .xls / .properties
# please ADD that as well
ADD src/main/java/data/data.xlsx        data.xlsx

# ADD suite files
ADD test-functional.xml				test-functional.xml
ADD test-parallel.xml				test-parallel.xml
ADD test-regression.xml             test-regression.xml
ADD test-smoke.xml                  test-smoke.xml

# ADD health check script
ADD healthcheck.sh                  healthcheck.sh

# BROWSER
# HUB_HOST
# MODULE

ENTRYPOINT sh healthcheck.sh
#ENTRYPOINT java -cp selenium-docker.jar:selenium-docker-sources.jar:selenium-docker-tests.jar:libs/* org.testng.TestNG $MODULE