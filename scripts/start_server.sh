#!/bin/bash
echo "Starting the awslearn-codepipeline application"
nohup java -jar /opt/awslearn-codepipeline/awslearn-codepipeline-build-*.jar > /opt/awslearn-codepipeline/logs/app.log 2>&1 &
