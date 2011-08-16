#!/bin/bash
echo -n 'VAR1: '
read VAR1
echo -n 'VAR2: '
read VAR2

if test $VAR1 -eq $VAR2
then
	echo 'equal'
else
	echo 'not equal'
fi
