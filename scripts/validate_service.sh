#!/bin/bash
echo "Validating that the awslearn-codepipeline application is running"
curl -f http://localhost:8080/actuator/health  # Adjust health check URL if needed
