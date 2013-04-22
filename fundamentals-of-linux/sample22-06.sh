#!/bin/bash

readonly SUFFIX='.bak'

FILELIST=`find . -maxdepth 1 -name '*' -type f`

if [ $? -ne 0 ]; then exit 1; fi

for fname_from in $FILELIST
do
	fname_to="$fname_from.bak"
	cp $fname_from $fname_to
done	
exit 0
