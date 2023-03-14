#!/bin/bash

dockerComposeTypes=('mysql' 'app')

dockerComposeTypesArgs=()

#prepare args
for name in "$@"
do
    if [[ " ${dockerComposeTypes[@]} " =~ " ${name} " ]]; then
        # whatever you want to do when arr contains value
#        if [ "$name" == "vpn" ]; then
#            dockerType='host';
#        else
#            dockerType=$name
#        fi

        dockerType=$name
        if [[ ! " ${dockerComposeTypesArgs[@]} " =~ " ${dockerType} " ]]; then
            dockerComposeTypesArgs+=( $dockerType )
        fi
    fi
done

#default, without arg
# todo

#prepare cmd
cmd="docker-compose";
for name in "${dockerComposeTypesArgs[@]}"
do
    cmd="$cmd -f docker-compose-$name.yml"
done
cmd="$cmd up -d"

echo "Start cmd : $cmd"
eval $cmd