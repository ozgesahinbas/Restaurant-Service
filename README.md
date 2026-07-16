# Restaurant Service

Restaurant Service is a Spring Boot microservice developed for restaurant management.  
The application provides REST APIs for creating, listing, retrieving, updating, and deleting restaurants. Restaurant data is stored in Couchbase Capella.

## Technologies

- Java 21
- Spring Boot
- Spring Web
- Spring Data Couchbase
- Couchbase Capella
- Spring AOP
- Bean Validation
- Lombok
- JUnit 5
- Mockito
- MockMvc
- JaCoCo
- Maven
- Postman

## Features

The application supports the following operations:

- Create a restaurant
- Get all restaurants
- Get a restaurant by ID
- Update a restaurant
- Delete a restaurant

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/restaurants` | Create a new restaurant |
| GET | `/restaurants` | Get all restaurants |
| GET | `/restaurants/{id}` | Get a restaurant by ID |
| PUT | `/restaurants/{id}` | Update a restaurant |
| DELETE | `/restaurants/{id}` | Delete a restaurant |

The application runs locally on:

`http://localhost:8080`

## Couchbase Configuration

The application uses Couchbase Capella for data persistence.

The following environment variables must be configured before running the application:

```text
COUCHBASE_CONNECTION_STRING
COUCHBASE_USERNAME
COUCHBASE_PASSWORD
```

Example configuration:

```yaml
spring:
  couchbase:
    connection-string: ${COUCHBASE_CONNECTION_STRING}
    username: ${COUCHBASE_USERNAME}
    password: ${COUCHBASE_PASSWORD}

  data:
    couchbase:
      bucket-name: restaurant-service
      scope-name: restaurant
```

Couchbase structure used by the application:

```text
Bucket: restaurant-service
Scope: restaurant
Collection: restaurants
```

> Do not commit real Couchbase credentials to the repository.

## Running the Application

Make sure Java 21 is installed.

Clone the repository and navigate to the project directory.

Configure the required Couchbase environment variables and run:

```bash
./mvnw spring-boot:run
```

The application will start on port `8080`.

## AOP Logging

Spring AOP is used for cross-cutting logging operations.

The `LoggingAspect` provides:

- Request logging for controller methods
- Execution time logging for service methods
- Exception logging for service layer exceptions

## Testing

The project contains unit tests for:

- Service layer
- Controller layer
- Exception handling
- Validation cases
- AOP logging
- Couchbase TLS configuration
- Application startup

Tests can be executed with:

```bash
./mvnw test
```

## Test Coverage

JaCoCo is used to generate the test coverage report.

Run:

```bash
./mvnw clean test
```

The generated HTML coverage report can be found at:

```text
target/site/jacoco/index.html
```

The project currently has **100% test coverage**.

## API Examples

A Postman collection containing CRUD API examples is included in the `postman` directory.

The collection contains examples for:

- Create Restaurant
- Get All Restaurants
- Get Restaurant By ID
- Update Restaurant
- Delete Restaurant

Import the collection into Postman to test the API endpoints.

## Project Structure

```text
src
├── main
│   ├── java/com/example/restaurantservice
│   │   ├── aspect
│   │   ├── config
│   │   ├── controller
│   │   ├── dto
│   │   ├── entity
│   │   ├── exception
│   │   ├── repository
│   │   └── service
│   └── resources
└── test
    └── java/com/example/restaurantservice
        ├── aspect
        ├── config
        ├── controller
        ├── exception
        └── service
        └── RestaurantServiceApplicationTest.java 
```

## Coverage

All application classes, methods, lines, and branches covered by the test suite have **100% JaCoCo coverage**.