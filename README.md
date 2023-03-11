# cron-expression-parser
Cron expression parser for recruitment process. The project uses jdk-19 and kotlin version 1.8.10

## Installing kotlin runtime on Ubuntu 22.04
- Assign running permissions to `install-kotlin-runtime.sh` script `chmod +x ./docker/install-kotlin-runtime.sh`
- Use prepared script: `./docker/install-kotlin-runtime.sh`
The script will install also jdk-19 along with other required dependencies

## Compiling
- Assign running permissions to `mvnw` script `chmod +x mvnw`
- Use `./mvnw package`

## Running project
- Assign running permissions to `run.sh` script `chmod +x run.sh`
- Use prepared script: `./run.sh "<program argument>"`, eg. `./run.sh "*/15 0 1,15 * 1-5 /usr/bin/find"` (note that `"` sign is important) <br/>

## Side notes
- Dockerfile has been added for testing purposes to ensure running instructions are correct and users will be able to run project in clean Linux environment (accordingly to the requirements)
