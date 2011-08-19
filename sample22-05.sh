#!/bin/bash

YEAR=$1

if test $YEAR -ge 1868 -a $YEAR -le 1911
then
	GENGO='M'	# 元号
	YEAR_J=`expr $YEAR - 1867`	# 和暦
elif test $YEAR -ge 1912 -a $YEAR -le 1925
then
	GENGO='T'
	YEAR_J=`expr $YEAR - 1911`
elif test $YEAR -ge 1926 -a $YEAR -le 1988
then
	GENGO='S'
	YEAR_J=`expr $YEAR - 1925`
else
	GENGO='H'
	YEAR_J=`expr $YEAR - 1988`
fi

echo $GENGO $YEAR_J
