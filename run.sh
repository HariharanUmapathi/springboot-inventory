#!/bin/bash

ENV_FILE=".env"

# Check if .env file exists
if [[ ! -f "$ENV_FILE" ]]; then
  echo "❌ Error: $ENV_FILE file not found."
  exit 1
fi

# Check for invalid lines (no = or spaces around =)
invalid_lines=$(grep -Ev '^\s*#|^\s*[A-Za-z_][A-Za-z0-9_]*=[^=]*$' "$ENV_FILE")

if [[ -n "$invalid_lines" ]]; then
  echo "⚠️ Warning: The following lines in $ENV_FILE are not valid and will be ignored:"
  echo "$invalid_lines"
  echo
fi

# Load the .env file safely
set -a
source "$ENV_FILE"
set +a

echo "✅ Environment variables loaded from $ENV_FILE."
mvn spring-boot:run
