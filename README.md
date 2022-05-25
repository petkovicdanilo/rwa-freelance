# Freelance API

This project is an API for freelancer application written in Spring Boot.
It was developed as a part of web application development course on
University of Science and Mathematics in Nis, Serbia.

## Setting up

In order to run this application you need to set up:
- `java` development environment (IntelliJ preferred)
- `maven`
- `mysql` database

After you clone the project make sure to sync maven.

### Configuration variables

You will need to set up following environment variables in order to 
successfully run this application:
- `FREELANCE_DB_HOST` - hostname or ip address of `mysql` server
- `FREELANCE_DB` - name of database on which you want to operate
- `FREELANCE_DB_USERNAME` - database server user
- `FREELANCE_DB_PASSWORD` - password for database user
- `FREELANCE_SMTP_HOST` - hostname or ip address of SMTP server for sending email
- `FREELANCE_SMTP_PORT` - port on which you want to communicate with SMTP server
- `FREELANCE_SMTP_USERNAME` - SMTP username
- `FREELANCE_SMTP_PASSWORD` - SMTP password

### Profiles

Application supports 2 profiles
- `dev`
- `production`

To set up profile set `spring_profiles_active` environment variable
to desired value.

In `dev` profile you'll get detailed logging as well as actuator details
about application.

### Database initialization

In `dev` profile you can trigger database initialization by passing 
`--init-db` command line argument. If you are running application
through `mvn spring-boot:run` or `mvnw spring-boot:run` add `-Dspring-boot.run.arguments=--init-db` 
command line option.

This will populate database with dummy data for development purposes.

