#!/usr/bin/env bash
JAVA_PROGRAM_ARGS="$*"
./mvnw -q exec:java -Dexec.mainClass="MainKt" -Dexec.args="$JAVA_PROGRAM_ARGS"
