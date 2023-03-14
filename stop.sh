#!/bin/bash

dockerComposeTypes=('mysql' 'app')

#prepare cmd
cmd="docker-compose ";
for name in "${dockerComposeTypes[@]}"
do
    cmd="$cmd -f docker-compose-$name.yml"
done
cmd="$cmd stop"

echo "Stop cmd : $cmd"
eval $cmd