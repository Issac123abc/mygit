#!/bin/bash
cd $(dirname $0)
pid=$(ps -elf|grep common-service|grep -v grep|awk '{print $4}')
if [[ -n "$pid" ]];then
  echo "kill common-service";
  kill -9 $pid
fi
echo "common-service stoped"

