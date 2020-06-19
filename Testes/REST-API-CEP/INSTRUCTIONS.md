# Zup - Recruiting Challenge

This document contains:
1. Instructions to test and run the challenge solution.
2. Explanation on the reasons behind Library, Framework and Implementation choices.
3. Further considerations about the problem description and expected answers.

## Testing and Running Instructions

### Requirements

To build, test and run this project your OS must be Unix-based and have Java SDK and Docker runtime installed.

### Testing

Open the command line inside the project folder and run:
```
$ ./test.sh
```

### Building

Open the command line inside the project folder and run:
```
$ ./build.sh
```

### Running

#### Java-way

Open the command line inside the project folder and run:
```
$ ./run-java.sh
```
The backend server will start, listening on 8080. 

#### Maven-way

Open the command line inside the project folder and run:
```
$ ./run-maven.sh
```
The backend server will start, listening on 8080. 


#### Docker-way

Open the command line inside the project folder and run:
```
$ ./build-docker.sh
```
This command generates a Docker image labeled `local-zup-challenge-ramonfacchin-zip-backend`.
After that you can run the Docker container with the following command:
```
$ ./run-docker.sh
```
The backend server will start, listening on 8080. 

#### Docker-way (daemon mode)

Open the command line inside the project folder and run:
```
$ ./build-docker.sh
```
This command generates a Docker image labeled `local-zup-challenge-ramonfacchin-zip-backend`.
After that you can run the Docker container with the following command:
```
$ ./run-docker-daemon.sh
```
The backend server will start, listening on 8080 and will keep running even if the terminal is closed. To stop the server, open the command line inside the project folder and run:
```
$ ./stop-docker-daemon.sh
```

### Viewing API Documentation

Once the server is started, the API Documentation can be viewed at [localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Explanations

### Java

Java is among the most popular and stable programming languages nowadays. With plenty of support and documentation spread over large communities, most of them with Open Source enthusiasts. But, given the small scope of the challenge, any modern programming language could be chosen.

### Spring Boot Starter

With almost no configuration and little knowledge about conventions it is possible to quicly enable a microservice and deploy it with Spring. And it is thanks to this ease of use that Spring is, nowadays, probably the most popular Java Framework for Web Development.

With a few dependencies developers can enable tools to write less boiler plate code capable of handling persistence, IoC, REST web services, unit testing and errors.
The main contestant is Java EE here. But, although Java EE presents the same capabilities (even more), it relies on not so well documented "fast enablers" (Thorntail) or on heavyweight application servers that require a considerable time to set up before running - and this is where Spring overshadows Java EE.

### Lombok

Using Lombok is almost self-explanatory. It can not get more obvious than "avoid boiler-plate code".

### JPA Persistence and H2

Given the small scope of the problem, and the suggestion that a Database was mandatory, the quickest choice to handle Database persistence was to use an in-memory relational database and take advantage of Spring JPA Repositories - not that a relational database was the optimal choice for this scenario, but the tools provided by Spring make relational databases easier to handle.

### Swagger / OpenAPI Documentation

The challenge defines as optional to develop a Frontend so, although no Frontend was made, an API documentation is available following the OpenAPI Standard. This way, even though there is no Frontend, endpoints can be tested through the *Try it out* button inside each endpoint documentation.  

## Further Considerations

### About Exception Handling

Spring's built-in exception handling follows a convention that was not compatible with the error responses demanded by the challenge. Therefore, custom exception classes and a custom exception handler were created just to show the "Spring way" of returning non-default error responses. It would be simpler and quicker to simply handle errors adding if/else statements but in a real project that kind of approach leads to large and unreadable chunks of code and harder maintenance.

### UUID as Primary Keys

Since there is no *de facto* relational DBMS, UUIDs were chosen as primary keys to avoid DBMS dependant ID generation strategies. 