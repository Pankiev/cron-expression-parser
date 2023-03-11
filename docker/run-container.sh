#!/bin/bash
SCRIPT_DIR=$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )
docker build "$SCRIPT_DIR" -t "ubuntu-with-kotlin"
docker run -dit --name "ubuntu-with-kotlin" ubuntu-with-kotlin
