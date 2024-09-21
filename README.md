# AWS Learning CodePipeline

## Overview

AWS Learning CodePipeline is a demo Spring Boot application designed for managing user, staff, and student records. This project showcases JWT authentication, CRUD operations, and integration with Swagger for API documentation.

## Features

- **JWT Authentication**: Secure user registration and login using JWT.
- **CRUD Operations**: Create, Read, Update, and Delete operations for staff and student records.
- **Public Pages**: Public-facing pages for login, signup, and password recovery.
- **Swagger Integration**: API documentation and testing using Swagger UI.
- **Spring Boot**: Built on the Spring Boot framework for easy setup and management.

## Prerequisites

- Java 17
- Maven
- MongoDB instance (optional for persistence)

## Setup

1. **Clone the repository**:

    ```bash
    git clone https://github.com/learning-orgs/awslearning-codepipeline.git
    cd awslearning-codepipeline
    ```

2. **Configure application properties**:

    - Update the `src/main/resources/application.properties` with necessary configurations (e.g., database connection).

3. **Build and run the application**:

    ```bash
    ./mvnw clean install
    ./mvnw spring-boot:run
    ```

## Endpoints

### Public Endpoints
- **Get Public Page**: `GET /`
- **Get Login Page**: `GET /login`
- **Get Signup Page**: `GET /signup`
- **Post Signup**: `POST /signup`
- **Get Forgot Password Page**: `GET /forgot-password`
- **Post Forgot Password**: `POST /forgot-password`

### User Management Endpoints
- **Register User**: `POST /api/v1/register`
- **Login User**: `POST /api/v1/login`

### Staff Management Endpoints
- **Get All Staff**: `GET /api/v1/staff`
- **Get Staff by ID**: `GET /api/v1/staff/{id}`
- **Create Staff**: `POST /api/v1/staff`
- **Update Staff**: `PUT /api/v1/staff/{id}`
- **Delete Staff**: `DELETE /api/v1/staff/{id}`

### Student Management Endpoints
- **Get All Students**: `GET /api/v1/admin/students`
- **Get Student by ID**: `GET /api/v1/admin/students/{id}`
- **Create Student**: `POST /api/v1/admin/students`
- **Update Student**: `PUT /api/v1/admin/students/{id}`
- **Delete Student**: `DELETE /api/v1/admin/students/{id}`

## Swagger UI

Access the Swagger UI for API documentation at: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Testing

- **Unit Tests**: Use `./mvnw test` to run unit tests.
- **Integration Tests**: Tests are set up to ensure functionality of the application.

## License

This project is licensed under the [MIT License](https://opensource.org/licenses/MIT).

## Contact

For questions or issues, please contact [Your Name](mailto:your-email@example.com).

## Acknowledgements

- **Spring Boot**: [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- **Swagger**: [SpringDoc OpenAPI](https://springdoc.org/)
- **JWT**: [JWT.io](https://jwt.io/)
