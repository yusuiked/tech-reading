#!/bin/bash
SUM=0
for i in `seq 1 $1`
do
	SUM=`expr $SUM + $i`
done

echo 'SUM:' $SUM
