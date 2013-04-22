#!/bin/bash

if test $# -ne 1
then
	echo 'error: 引数は1個でなければなりません'
	exit 1
fi

source './sample22_functions.sh'
my_func_check_integer $1
if test $? -ne 0
then
	echo 'error: 引数は正の整数でなければなりません'
	exit 1
fi

if test $1 -lt 1868
then
	echo 'error: 明治以前の元号には対応していません'
	exit 1
else
	YEAR=$1
fi

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
exit 0
