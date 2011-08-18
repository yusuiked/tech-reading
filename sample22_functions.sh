#!/bin/bash

my_func_check_integer() {
	if `echo $1 | grep '[^0-9]' 1>/dev/null 2>&1`
	then
		echo 'error: 引数は整数でなければなりません' 1>&2
		exit 1
	fi
}
