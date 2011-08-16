#!/bin/bash
echo -n 'VAR1: '
read VAR1
echo -n 'VAR2: '
read VAR2

for i in $VAR1 $VAR2
do
	if echo $i | grep '[^0-9]' > /dev/null
	then
		echo 'error: VAR1 or VAR2 is wrong.' 1>&2
		exit 1
	fi
done
if test $VAR1 -eq $VAR2
then
	echo 'equal'
elif test $VAR1 -gt $VAR2
then
	echo 'VAR1 is greater than VAR2. ($VAR1 > $VAR2)'
else
	echo 'VAR1 is less than VAR2. ($VAR1 < $VAR2)'
fi
