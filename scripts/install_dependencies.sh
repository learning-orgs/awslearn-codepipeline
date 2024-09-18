#!/bin/bash
set -e

# Update system packages
sudo yum update -y

# Install Amazon Corretto 17 (Java 17)
sudo yum install -y java-17-amazon-corretto

# Confirm Java installation
java -version
