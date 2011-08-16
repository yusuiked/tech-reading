#!/bin/bash
echo -n 'Continue? [yes/no]: '
read VAR

echo $VAR

case $VAR in
	[Yy][Ee][Ss]|[Yy])
		echo '----- yes -----'
		;;
	[Nn][Oo]|[Nn])
		echo '----- no -----'
		;;
	*)
		echo '----- others -----'
		;;
esac
