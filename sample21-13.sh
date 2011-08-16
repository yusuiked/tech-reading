#!/bin/bash
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
