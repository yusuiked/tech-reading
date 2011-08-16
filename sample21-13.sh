#!/bin/bash

if test $# -ne 1
then
	echo '----- wrong option -----'
	exit 1
fi

case $1 in
	-a)
		echo '----- option a -----'
		;;
	-b)
		echo '----- option b -----'
		;;
	*)
		echo '----- not supported -----'
		;;
esac
