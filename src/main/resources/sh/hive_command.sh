#!/usr/bin/env bash
source /etc/profile
source ~/.bash_profile
echo "hello"
COMMAND=$1
echo $COMMAND
hivehome=`which hive`
echo $hivehome
cmd="$hivehome -f $COMMAND"
echo $cmd
eval $cmd