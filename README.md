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
    - using `mvn tomcat7:run`
    - using `mvn heroku:run-war`
    - using `webapp-runner`:
        1. run `mvn package`
        2. run `java -jar target/dependency/webapp-runner.jar target/itis-pita.war` in project root. 
            You can also configure webapp runner using command line arguments, run `webapp-runner.jar --help` for help. 


## How to contribute

For each feature, create a separate branch.

After a feature is fully implemented, create a pull request to `dev` branch.

The above process is subject to change.