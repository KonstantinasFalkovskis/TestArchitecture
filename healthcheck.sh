#!/usr/bin/env bash
# Environment Variables
#=========
# hub
# port
# environment
# platform
# browserName
# module

# echo "Checking if hub is ready - $hub"
echo "Checking if hub is ready"
# while [ "$( curl -s http://$hub:port/wd/hub/status | jq -r .value.ready )" != "true" ]
while [ "$( curl -s http://192.168.0.106:4444/wd/hub/status | jq -r .value.ready )" != "true" ]
do
	sleep 1
done

# start the java command
java -cp Airbnb-java-selenium-testng-build.jar:Airbnb-java-selenium-testng-build-sources.jar: \
Airbnb-java-selenium-testng-build-tests.jar:libs/* \
   # -Dhub=$hub \
   # -Dport=$port \
   # -Denvironment=$environment \  #need to investigate is Local testing needed or not. If not remove ENV -param
   # -Dplatfrom=$platform \  #needed if another mashines - slaves are using in testing
   # -DbrowserName=$browserName \
    org.testng.TestNG $MODULE