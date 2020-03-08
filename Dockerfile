FROM openjdk:8u191-jre-alpine3.8

RUN apk add curl jq

# Workspace
WORKDIR /usr/share/tests

# ADD .jar under target from host
# into this image
ADD target/Airbnb-java-selenium-testng-build.jar 			Airbnb-java-selenium-testng-build.jar
ADD target/Airbnb-java-selenium-testng-build-sources.jar 	Airbnb-java-selenium-testng-build-sources.jar
ADD target/Airbnb-java-selenium-testng-build-tests.jar 	    Airbnb-java-selenium-testng-build-tests.jar
ADD target/libs						                    	libs

# in case of any other dependency like .csv / .json / .xls
# please ADD that as well
ADD src/main/java/data/data.xlsx    data.xlsx

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