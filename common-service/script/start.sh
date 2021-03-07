#!/bin/bash
cd $(dirname $0)
export APP_NAME="common-service"

nohup java -DappName=${APP_NAME} -Xms128m -Xmx20000m -jar common-api-1.0-SNAPSHOT.jar &>/dev/null &
