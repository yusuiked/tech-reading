#!/bin/bash
echo -n 'VAR1: '
read VAR1
echo -n 'VAR2: '
read VAR2

if test $VAR1 -eq $VAR2
then
	echo 'equal'
elif test $VAR1 -gt $VAR2
then
	echo 'VAR1 is greater than VAR2. ($VAR1 > $VAR2)'
else
	echo 'VAR1 is less than VAR2. ($VAR1 < $VAR2)'
fi
