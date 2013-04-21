#!/bin/bash
echo -n 'VAR: '
read VAR

test -z $VAR
echo 'status:' $?
