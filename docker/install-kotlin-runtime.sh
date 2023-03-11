#!/bin/bash
apt update
apt install curl unzip zip openjdk-19-jdk -y
curl -s "https://get.sdkman.io" | bash
source "/root/.sdkman/bin/sdkman-init.sh"
sdk install kotlin

