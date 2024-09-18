#!/bin/bash
set -e

# Define the health check URL (you might want to customize this)
APP_URL="http://localhost:8080/actuator/health"

# Check the health of the application
STATUS_CODE=$(curl --silent --output /dev/stderr --write-out "%{http_code}" "$APP_URL")

if [ "$STATUS_CODE" -ne 200 ]; then
  echo "Health check failed with status code $STATUS_CODE"
  exit 1
else
  echo "Health check passed with status code $STATUS_CODE"
fi
