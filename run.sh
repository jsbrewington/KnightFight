#!/bin/bash

# Run script for Knights & Fortunes Game

if [ ! -d "bin" ]; then
    echo "Project not built. Running build script..."
    ./build.sh
fi

echo "Starting Knights & Fortunes Game..."
java -cp bin com.knightgame.Main "$@"
