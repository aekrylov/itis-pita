# PITA - Portal for IT Administration

Spring based web app for ITIS administration

## Technologies used

- Spring
    - Spring Core
    - Spring MVC
    - Spring Data JPA
    - Spring Security
- Hibernate
- Freemarker
- PostgreSQL database

## How to run

0. Install tomcat and maven
1. Clone the repo
2. Copy [db.properties-template](./src/main/resources/db.properties-template) to db.properties and replace all placeholders with your actual values
3. Launch the server:
    - using `mvn tomcat7:run-war`
    - using `webapp-runner`:
        1. run `mvn package`
        2. run `java -jar target/dependency/webapp-runner.jar target/itis-pita.war` in project root. 
            You can also configure webapp runner using command line arguments, run `webapp-runner.jar --help` for help.
            
All above configurations allow for debugging in IDEA (simply add run configuration and debug as usual).

### Hot swap for classes in IDEA

IDEA can automatically reload the code (with some limitations). 
1. Run debug configuration
2. Make changes
3. Press Build project in IDEA
4. IDEA will ask if you want to reload classes (this can be configured in IDEA settings)

### Hot swap for templates and static files
1. Run application using `tomcat7:run-war`
2. Change resources
3. Run `mvn war:war`

## How to contribute

For each feature, create a separate branch.

After a feature is fully implemented, create a pull request to `dev` branch.

The above process is subject to change.