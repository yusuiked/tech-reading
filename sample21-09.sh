#!/bin/bash
echo -n 'Continue? [yes/no]: '
read VAR

echo $VAR

case $VAR in
	[Yy][Ee][Ss])
		echo '----- yes -----'
		;;
	[Nn][Oo])
		echo '----- no -----'
		;;
	*)
		echo '----- others -----'
		;;
esac
