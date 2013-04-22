#!/bin/bash

argument_test1() {
	echo '----- in "argument_test1()" -----'
	echo $1
	echo $2
	echo $*
	echo $#
	echo $0
}

argument_test2() {
	echo '----- in "argument_test2()" -----'
	echo $1
	echo $2
	echo $*
	echo $#
	echo $0
}

echo '----- main routine -----'
echo $1
echo $2
echo $*
echo $#
echo $0
argument_test1 'apple' 'banana'
argument_test2 'football'
echo '----- main routine -----'
echo $1
echo $2
echo $*
echo $#
echo $0
