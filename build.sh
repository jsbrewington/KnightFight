#!/bin/bash

# Build script for Knights & Fortunes Game

echo "Building Knights & Fortunes Game..."

mkdir -p bin

find src/main/java -name "*.java" > sources.txt

javac -d bin @sources.txt

if [ $? -eq 0 ]; then
    echo "Build successful!"
    rm sources.txt
else
    echo "Build failed!"
    rm sources.txt
    exit 1
fi
