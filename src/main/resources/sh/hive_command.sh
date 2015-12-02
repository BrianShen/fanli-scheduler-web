#!/usr/bin/env bash
COMMAND=$1
#export HADOOP_HOME=/hadoop/hadoop-2.4.1

#/hadoop/apache-hive-0.13.1-cdh5.2.1-bin/bin/hive -e "$COMMAND";

hive -e "$COMMAND"