# cron-expression-parser
Cron expression parser for recruitment process. The project uses jdk-19 and kotlin version 1.8.10

## Installing kotlin runtime on Ubuntu 22.04
- Use prepared script: `./docker/install-kotlin-runtime.sh`
The script will install also jdk-19 along with other required dependencies

## Compiling
- Use `./mvnw compile`

## Running project
- Use prepared script: `./run.sh "<program arguments>"`, eg. `./run.sh "*/15 0 1,15 * 1-5 /usr/bin/find"` (note that `"` sign is important) <br/>

