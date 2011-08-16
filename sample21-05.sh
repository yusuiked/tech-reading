#!/bin/bash
VAR1=2
VAR2=5

test $VAR1 -eq $VAR2
echo 'status:' $?
