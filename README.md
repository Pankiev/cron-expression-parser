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
- I assumed that the task is about parsing the expressions on candidate's own. In real world scenario I would use an external library.
- I tried to design the code to allow the easiest extendability. Generic solutions are often a bit more complicated which is a negative side effect. I know that I `will be asked to extend
  the solution with additional features while onsite` and I want to go through as clean as possible. That being said, I tried not to overcomplicate when the gain was not significant
- Dockerfile has been added for testing purposes to ensure running instructions are correct and users will be able to run project in clean Linux environment (accordingly to the requirements)
- Most non-standard characters in cron expressions (L, W, #, ?) are not handled. They are used only in some implementations
- At this point I would mark the code as ready for review (but not for production use yet)
