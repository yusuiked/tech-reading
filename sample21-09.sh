#!/bin/bash
echo -n 'Continue? [yes/no]: '
read VAR

echo $VAR

case $VAR in
	yes)
		echo '----- yes -----'
		;;
	no)
		echo '----- no -----'
		;;
	*)
		echo '----- others -----'
		;;
esac
