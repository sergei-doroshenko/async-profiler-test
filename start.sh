#!/bin/sh
echo "Start working"
ls -la
cd /opt/
ls -la
nohup java -jar async-profiler-test.jar &
echo $!
ps aux
jps
tar xvzf async-profiler-1.6-linux-x64.tar.gz