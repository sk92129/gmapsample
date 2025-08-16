#!/bin/bash

# Script to read GMA_API_KEY from dev.json
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PROJECT_ROOT="$(dirname "$(dirname "$SCRIPT_DIR")")"
DEV_JSON_PATH="$PROJECT_ROOT/lib/dev.json"

if [ -f "$DEV_JSON_PATH" ]; then
    # Use Python to parse JSON and extract the API key
    API_KEY=$(python3 -c "
import json
import sys
try:
    with open('$DEV_JSON_PATH', 'r') as f:
        data = json.load(f)
        print(data.get('GMAP_API_KEY', ''))
except Exception as e:
    print('', file=sys.stderr)
    sys.exit(1)
")
    
    if [ $? -eq 0 ] && [ -n "$API_KEY" ]; then
        echo "$API_KEY"
    else
        echo "Error reading API key from dev.json" >&2
        exit 1
    fi
else
    echo "dev.json not found at $DEV_JSON_PATH" >&2
    exit 1
fi
