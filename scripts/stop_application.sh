#!/bin/bash
set -e

# Find the running Java process and kill it
sudo pkill -f 'awslearn-codepipeline.jar' || echo "No application to stop."
