#!/bin/bash

# 引数が 0 または正の整数であるかを確認する
# 引数： 1つ（判定したい値）
# 戻り値： なし
# 終了ステータス： 条件にマッチしたら0，しなかったら1
my_func_check_integer() {
	if `echo $1 | grep '[^0-9]' 1>/dev/null 2>&1`
	then
		return 1
	else
		return 0
	fi
}
