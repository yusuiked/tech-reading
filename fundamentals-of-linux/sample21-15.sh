#!/bin/bash
SUM=0
if test $# -ne 1
then
	echo 'error:引数は1個でなければなりません' 1>&2
	exit 1
fi
if `echo $1 | grep '[^0-9]' 1>/dev/null`
then
	echo 'error:引数は整数でなければなりません' 1>&2
	exit 1
fi
if test $1 -lt 1
then
	echo 'error:引数は1以上である必要があります' 1>&2
	exit 1
fi
for i in `seq 1 $1`
do
	SUM=`expr $SUM + $i`
done

echo 'SUM:' $SUM
