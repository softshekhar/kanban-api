# Information
This is a sample SPRING BOOT CRUD Application to Add/Update/Delete/Get tasks

# Instructions
1. Open pom.xml with Intelij Idea or Eclipse.
2. Run Main.java
3. Open browser on http://[ip of localhost]:[port] port as mentioned in application.properties under resources
4. Frontent for this app is on https://github.com/softshekhar/kanban-frontend

# Instructions for running as docker container(optional)
1. We will now use cloud native build packs to build docker image. Using this we avoid creating a Dockerfile. Spring Boot 2.3.x and newer can build a container for you. Build image using below command\
./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=kanban-api
2. run image using below command\
docker run -m 1G -p [port number on Docker host]:[port number on docker container] kanban-api

# Dependencies
1. Keycloak. You can spin up local keycloak using docker. refer https://www.keycloak.org/getting-started/getting-started-docker



