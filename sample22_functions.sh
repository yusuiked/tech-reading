#!/bin/bash

my_func_check_integer() {
	if `echo $1 | grep '[^0-9]' 1>/dev/null 2>&1`
	then
		return 1
	else
		return 0
	fi
}
