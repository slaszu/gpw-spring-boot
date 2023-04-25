#!/bin/bash

cmd="docker-compose -f docker-compose-app.yml build --progress=plain --no-cache"

echo $cmd

$cmd