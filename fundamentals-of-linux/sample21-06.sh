#!/bin/bash
echo -n 'VAR1: '
read VAR1
echo -n 'VAR2: '
read VAR2

test $VAR1 = $VAR2
echo 'status:' $?
