#!/bin/bash
echo "Stopping existing application service if running"
service awslearn-codepipeline stop || true  # Replace 'awslearn-codepipeline' with your actual service name
