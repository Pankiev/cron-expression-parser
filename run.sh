#!/usr/bin/env bash
JAVA_PROGRAM_ARGS="$*"
SCRIPT_DIR=$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )
java -jar "$SCRIPT_DIR/target/cron-expression-parser-1.0-SNAPSHOT-jar-with-dependencies.jar" "$JAVA_PROGRAM_ARGS"
