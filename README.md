# Bookstore API

A Spring Boot REST API application for managing a bookstore with JWT-based authentication.

## Features

- **User Authentication**: JWT-based authentication system
- **Book Management**: CRUD operations for books
- **User Management**: User registration and profile management
- **Security**: Spring Security with JWT token validation
- **Database**: JPA/Hibernate with repository pattern
- **API Documentation**: RESTful API endpoints

## Technologies Used

- **Java**: Programming language
- **Spring Boot**: Application framework
- **Spring Security**: Authentication and authorization
- **Spring Data JPA**: Data access layer
- **JWT**: JSON Web Token for authentication
- **Maven**: Build tool and dependency management
- **H2/MySQL**: Database (configurable)

## Project Structure

```text
src/main/java/com/kaushik/api/bookstore/
├── config/          # Security and JWT configuration
├── controller/      # REST controllers
├── dto/            # Data Transfer Objects
├── entity/         # JPA entities
├── mapper/         # Entity-DTO mappers
├── repository/     # Data access repositories
└── service/        # Business logic services
```

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

### Running the Application

1. Clone the repository:

   ```bash
   git clone <repository-url>
   cd bookstore
   ```

2. Build the project:

   ```bash
   mvn clean install
   ```

3. Run the application:

   ```bash
   mvn spring-boot:run
   ```

   Or use the provided batch file:

   ```bash
   start-app.bat
   ```

The application will start on `http://localhost:8080`

### Testing the API

Use the provided PowerShell scripts to test the API:

- `test-auth.ps1` - Test authentication endpoints
- `test-api.ps1` - Test book management endpoints

## API Endpoints

### Authentication

- `POST /auth/login` - User login
- `POST /auth/register` - User registration

### Books

- `GET /books` - Get all books
- `GET /books/{id}` - Get book by ID
- `POST /books` - Create new book
- `PUT /books/{id}` - Update book
- `DELETE /books/{id}` - Delete book

### Users

- `GET /users/profile` - Get user profile
- `PUT /users/profile` - Update user profile

## Configuration

The application can be configured through `application.properties`:

- Database configuration
- JWT secret and expiration
- Server port and other Spring Boot settings

## Docker Support

A `compose.yaml` file is included for containerized deployment.

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## License

This project is licensed under the MIT License.
