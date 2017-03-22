# PITA - Portal for IT Administration

Spring based web app for ITIS administration

## Technologies used

- Spring
    - Spring Core
    - Spring MVC
    - Spring Data JPA
- Hibernate
- Freemarker

## How to run

0. Install tomcat and maven
1. Clone the repo
2. Copy [db.properties-template](./src/main/resources/db.properties-template) to db.properties 
and replace all placeholders with your actual values
3. Run `mvn package`
4. Launch webapp runner as an ordinary jar with the following command:
`java -jar target/dependency/webapp-runner.jar target/itis-pita.war`

(You can also run the app using `mvn tomcat7:run` or `mvn heroku:run-war`, but it is not recommended and may be removed in future)

Your app will keep running in the console window, typically it is fully loaded after the following line appeared:

`INFO: Starting ProtocolHandler ["http-nio-8080"]`

You can add run configuration of type 'JAR Application' to Idea:
- path to jar is `target/dependency/webapp-runner.jar`
- program arguments are `target/itis-pita.war`
- working directory is project root
- before launch: add Maven goal `package`

## How to contribute

For each feature, create a separate branch.

After a feature is fully implemented and tested, 
create a pull request to `master` branch.

The above process is subject to change.

## How to deploy

1. Add Heroku remote
2. Push to heroku `master`. Heroku will build the app using Maven and launch it