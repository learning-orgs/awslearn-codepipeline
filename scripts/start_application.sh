#!/bin/bash
set -e

# Navigate to the application directory
cd /home/ec2-user/awslearn-codepipeline

# Run the Spring Boot application in the background
nohup java -jar awslearn-codepipeline.jar > /dev/null 2>&1 &
