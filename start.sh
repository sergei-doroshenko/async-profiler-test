#!/bin/sh
echo "Start working"
ls -la
cd /opt/
ls -la
#nohup java -jar async-profiler-test.jar &
#echo $!
java -jar async-profiler-test.jar