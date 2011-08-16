#!/bin/bash
echo -n 'VAR1: '
read VAR1
echo -n 'VAR2: '
read VAR2

if echo $VAR1 | grep '[^0-9]' > /dev/null
then
	echo 'error: VAR1 or VAR2 is wrong.' 1>&2
	exit 1
fi
if echo $VAR2 | grep '[^0-9]' > /dev/null
then
	echo 'error: VAR1 or VAR2 is wrong.' 1>&2
	exit 1
fi

if test $VAR1 -eq $VAR2
then
	echo 'equal'
elif test $VAR1 -gt $VAR2
then
	echo 'VAR1 is greater than VAR2. ($VAR1 > $VAR2)'
else
	echo 'VAR1 is less than VAR2. ($VAR1 < $VAR2)'
fi
