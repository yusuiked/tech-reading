#!/bin/bash

while test -z $LETTER
do
	echo -n 'Please type your name: '
	read LETTER
done

echo 'Your name is: ' $LETTER
