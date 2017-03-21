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
2. Copy [db.properties-template](./src/main/resources/db.properties-template) to db.properties and replace all placeholders with your actual values
3. Launch embed server using `mvn heroku:run-war`


## How to contribute

For each feature, create a separate branch.

After a feature is fully implemented and tested, 
create a pull request to `master` branch.

The above process is subject to change.