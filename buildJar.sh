#!/bin/bash

if [ ! -d builds ]
then
	mkdir builds
else
	echo "cant create build dir"
	exit 1
fi



jar -cvfm builds/SpellManager.jar Manifest.txt -C bin .

cp -r Languages builds/ 

cd builds
java -jar SpellManager.jar
