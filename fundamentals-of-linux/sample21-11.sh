#!/bin/bash
SUM=0

for i in {1..5}
do
	SUM=`expr $SUM + $i`
done
echo 'SUM:' $SUM
