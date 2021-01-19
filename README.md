# Spring vs Quarkus

The goal of this repository is to provide a reduced number of applications that are able to show Quarkus key points concisely while comparing it to a traditional Spring application:
1. spring-rest-api is built with Spring. It provides a REST API and persists data on a PostgreSQL database;
2. quarkus-rest-api is built with Quarkus in an imperative way and it's very similar to spring-rest-api. It provides a REST API and persists data on a PostgreSQL database;
3. quarkus-reactive-rest-api is built with Quarkus in a reactive way. It provides a REST API, persists data on a PostgreSQL database, generates an OpenAPI specification while providing a representation using Swagger UI and generates metrics on OpenMetrics specification while providing a representation using Prometheus.

# Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

## Prerequisites

```bash
Java 11
Maven 3.6.1
Docker 19.03.5
```

# Running

## Create Docker network
```bash
$ docker network create book-store-network
```

## Build & run Spring application
Generate fat .jar:
```bash
$ mvn clean package spring-boot:repackage
```

Build docker compose w/ database and application containers:
```bash
$ docker-compose build
```

Run docker compose w/ database and application containers:
```bash
$ docker-compose up
```

## Build & run Quarkus application
Run with live reload (test and dev purposes):
```bash
$ mvn compile quarkus:dev
```

Generate fat .jar:
```bash
$ mvn clean package
```

Or generate native executable:
```bash
$ mvn clean package -Pnative -Dquarkus.native.container-build=true -Dquarkus.native.builder-image=quay.io/quarkus/ubi-quarkus-native-image:19.3.1-java11
```

Build docker compose w/ database, migrations, monitoring and application containers:
```bash
$ docker-compose build
```

Run docker compose w/ database, migrations, monitoring and application containers:
```bash
$ docker-compose up
```                          

## Run stress tests
Run Apache Bench stress tests:
```bash
$ ab -n 10000 -c 10 http://localhost:8080/api/v1/books 
```

If you don't have Apache Bench, run the following Docker container:
```bash
$ docker run --rm jordi/ab -n 10000 -c 10 http://localhost:8080/api/v1/books/ 
```

## Other informations
Useful endpoints:

- POST http://localhost:8080/api/v1/books
- GET http://localhost:8080/api/v1/books
- GET http://localhost:8080/api/v1/books/{id}
- PUT http://localhost:8080/api/v1/books/{id}
- DELETE http://localhost:8080/api/v1/books/{id}
- GET http://localhost:8080/health (only on Quarkus applications)
- GET http://localhost:8080/metrics (only on Quarkus applications)
- GET http://localhost:8080/open-api (only on Quarkus applications)
- GET http://localhost:8080/swagger-ui (only on Quarkus applications)

Access Prometheus:
- GET http://localhost:9090 (only on Quarkus applications)

## Built With

* [Java](https://www.java.com/)
* [RxJava 2](http://reactivex.io/)
* [Maven](https://maven.apache.org/)
* [Docker](https://www.docker.com/)
* [Quarkus](https://quarkus.io/)
* [Spring](https://spring.io/)
* [PostgreSQL](https://www.postgresql.org/)
* [Flyway](https://flywaydb.org/)
* [Prometheus](https://prometheus.io/)
