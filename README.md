[]: # Title: Barber Shop API
[]: # Description: RESTful API for managing a barber shop, developed using Spring Boot.
# Barber Shop API

This project is a RESTful API for managing a barber shop, developed using Spring Boot. It includes functionality to manage barbers, items related to barbers (like tools), and provides authentication through JWT tokens. The project follows best practices for REST API development and includes integration with a MySQL database, OpenAPI for API documentation, and uses H2 for local testing.

## Table of Contents

- [Project Structure](#project-structure)
- [Technologies Used](#technologies-used)
- [Installation and Setup](#installation-and-setup)
- [Running the Project](#running-the-project)
- [API Endpoints](#api-endpoints)
  - [ItemBarber Endpoints](#itembarber-endpoints)
  - [Other Endpoints](#other-endpoints)
- [Database Configuration](#database-configuration)
- [License](#license)

---

## Project Structure

- **src/main/java/com/mentorly/barber_shop**  
  - **controller**: Contains the REST controllers that handle HTTP requests and responses.
  - **dto**: Data Transfer Objects used for transferring data between layers.
  - **entity**: The entity classes representing the database tables.
  - **mapper**: Classes responsible for mapping between entities and DTOs.
  - **repository**: Contains the repository interfaces for database interactions using Spring Data JPA.
  - **service**: Service classes where the business logic is implemented.

- **src/main/resources**
  - Contains application configuration files such as `application.yml` for environment-specific settings.

- **src/test**
  - Contains unit and integration tests for the application.


- **Controller**: Handles the REST endpoints.
- **DTO**: Data Transfer Objects for the entities.
- **Entity**: Java classes mapped to database tables.
- **Mapper**: Converts entities to DTOs and vice versa (MapStruct).
- **Repository**: Interfaces for database operations using Spring Data JPA.
- **Service**: Business logic implementation.

---

## Technologies Used

- **Java 17**
- **Spring Boot 3.3.0**
- **Spring Data JPA**
- **JWT (JSON Web Tokens)**
- **MapStruct 1.5.5.Final**
- **Spring Security**
- **OpenAPI 3.0** for API documentation
- **H2 Database** (for local development)
- **MySQL** (for production database)
- **Lombok** (to reduce boilerplate code)

---

## Installation and Setup

### Prerequisites

- Java 17 or higher
- Maven 3.8.1 or higher
- MySQL (for production database)

### Setup Steps

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/barber-shop.git
   cd barber-shop
   ```

2. Build the project with Maven:
   ```bash
   mvn clean install
   ```

3. Configure the `application.properties` file for the database connection (see below for details).

4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

---

## Running the Project

To run the project locally, use the embedded **H2** database. You can use the following commands:

1. **Build the project**:
   ```bash
   mvn clean package
   ```

2. **Run the project**:
   ```bash
   mvn spring-boot:run
   ```

3. **Access API Documentation** (OpenAPI Swagger UI):
   Open your browser and navigate to:
   ```
   http://localhost:8080/swagger-ui.html
   ```

---

## API Endpoints

### ItemBarber Endpoints

| Method | Endpoint                      | Description                       |
|--------|-------------------------------|-----------------------------------|
| GET    | `/api/v1/item-barbers`         | Get a list of all item barbers    |
| GET    | `/api/v1/item-barbers/{id}`    | Get an item barber by ID          |
| POST   | `/api/v1/item-barbers`         | Create a new item barber          |
| PUT    | `/api/v1/item-barbers/{id}`    | Update an item barber by ID       |
| DELETE | `/api/v1/item-barbers/{id}`    | Delete an item barber by ID       |

### Other Endpoints

- **Authentication**: `/api/v1/authenticate` (POST) – for login and JWT token generation.
- **Barber Endpoints**: (similar structure to ItemBarber, just replace `item-barbers` with `barbers`)

---

## Database Configuration

### MySQL Setup (Production)

In the `src/main/resources/application.yml` file, configure the following for **MySQL**:

```yaml
spring:
  application:
    name: Barber-Shop API
  datasource:
    url: jdbc:h2:mem:testdb
    driver-class-name: org.h2.Driver
    username: sa
    password:
  h2:
    console:
      enabled: true
      path: /h2-console
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

  mail:
    host: localhost
    port: 1025
    username: admin
    password: admin
    properties:
      mail:
        smtp:
          trust: "*"
        auth: true
        starttls:
          enable: true
        connection:
          timeout: 5000
          writetimeout: 5000

security:
  jwt:
    secret-key: "RSjUBwnNFcjYzUFqFFDw1pCFbfZed5MC2QQVzs+CWeY="
    expiration-time: 86400000
mailing:
  frontend:
    activation:
      activationUrl:  http://localhost:4200/activate-account

logging:
  level:
    org:
      springframework:
        security: DEBUG
        web: DEBUG

server:
  port: 8080

```

### H2 Setup (Development)

The application is configured to use the **H2 database** by default for local development. You can access the H2 console at:

```
http://localhost:8080/h2-console
```

Use the following credentials:

- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: (leave blank)

# Contact and Social Links

- **GitHub**: [Starling Diaz](https://github.com/NSTLRD)
- **Website**: [Mentorly Blog](https://mentorly.blog/)
- **Linkedin**: [Linkedin](https://www.linkedin.com/in/starling-diaz-908225181/)
- **Mentorly Academy**: [Mentorly Academy](https://www.mentor-ly.com/)
- **Youtube**: [Mentorly Youtube](https://www.youtube.com/@Mentorly-e3b)
---

**Version:** 0.0.1-SNAPSHOT  
**Author:** Starling Diaz  
**License:** Open Source
```


### Explanation:

- The **Installation and Setup** section walks users through setting up the environment.
- The **API Endpoints** section details the available endpoints for `ItemBarber` and hints at similar endpoints for `Barber`.
- The **Database Configuration** section shows how to configure MySQL for production and H2 for local development.
- The **Contact and Social Links** section provides links to the author's GitHub, website, LinkedIn, Mentorly Academy, and YouTube channel.
- The **License** section states that the project is open source.