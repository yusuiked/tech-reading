#!/bin/bash
SUM=0
if test $# -ne 1
then
	echo 'error:引数は1個でなければなりません' 1>&2
	exit 1
fi
for i in `seq 1 $1`
do
	SUM=`expr $SUM + $i`
done

echo 'SUM:' $SUM
