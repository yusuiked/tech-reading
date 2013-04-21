#!/bin/bash
echo -n 'Please type your name: '
read LETTER
echo -n 'Thank you. Your name is '
echo $LETTER

echo '----- set -----'
set | grep 'LETTER'
echo '----- printenv -----'
printenv | grep 'LETTER'
