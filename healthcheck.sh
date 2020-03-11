#!/usr/bin/env bash
# Environment Variables
#=========
# HUB_HOST
# PORT
# environment
# platform
# browserName
# MODULE

# echo "Checking if " + "- $HUB_HOST " + "is ready"
echo "Checking if hub is ready"
# while [ "$( curl -s http://$HUB_HOST:$PORT/wd/hub/status | jq -r .value.ready )" != "true" ]
while [ "$( curl -s http://192.168.0.106:4444/wd/hub/status | jq -r .value.ready )" != "true" ]
do
	sleep 1
done

java -cp selenium-docker.jar:selenium-docker-sources.jar:selenium-docker-tests.jar:libs/* org.testng.TestNG $MODULE
## start the java command
#java -cp selenium-docker.jar:selenium-docker-sources.jar:selenium-docker-tests.jar:libs/* \
#   # -Dhub=$hub \
#   # -Dport=$port \
#   # -Denvironment=$environment \  #need to investigate is Local testing needed or not. If not remove ENV -param
#   # -Dplatfrom=$platform \  #needed if another mashines - slaves are using in testing
#   # -DbrowserName=$browserName \
#    org.testng.TestNG $MODULE