#!/bin/bash

readonly SUFFIX='.bak'

FILELIST=`find . -maxdepth 1 -type f`

for fname_from in $FILELIST
do
	if echo $fname_from | grep -v '\.bak$' 1>/dev/null 2>&1
	then
		fname_to="$fname_from$SUFFIX"
		cp $fname_from $fname_to
	fi
	if [ $? -ne 0 ]; then exit 1; fi
done	
exit 0
