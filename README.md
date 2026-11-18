# Recipe Management System

A web-based Recipe Management System built with Java and Spring Boot. The application allows users to manage recipes, ingredients, categories, and meal plans through a simple web interface.

## Technologies

- Java 21
- Spring Boot 4.0.9
- Spring MVC
- Spring Data JPA
- Spring Security
- Thymeleaf
- MySQL
- Maven
- HTML and CSS

## Features

- User registration and login
- Session-based authentication
- Role-based access control (USER and ADMIN)
- Create, view, edit, and delete recipes
- Manage recipe categories
- Manage ingredients
- Create, view, edit, and delete meal plans
- Server-side form validation
- Password encryption using BCrypt
- Custom exception handling

## Application Structure

The application follows a layered architecture:

- **Entity** – JPA entities representing the application data.
- **Repository** – Interfaces for database operations.
- **Service** – Business logic and data management.
- **Controller** – Handles HTTP requests and application navigation.
- **Config** – Application and security configuration.
- **Exception** – Custom exceptions and global exception handling.

## Database

The application uses MySQL.

Create a database named:

`recipe_app`

Configure your database connection in `src/main/resources/application.properties` with your local MySQL username and password.

## Running the Application

1. Clone the repository:

   `git clone https://github.com/Duzi2411/recipe-app.git`

2. Open the project in IntelliJ IDEA.

3. Make sure Java 21 and MySQL are installed.

4. Configure the database connection in `application.properties`.

5. Run the application using Maven:

   `./mvnw spring-boot:run`

   On Windows:

   `mvnw.cmd spring-boot:run`

6. Open the application in your browser:

   `http://localhost:8080`

## Author

Recipe Management System – Spring Fundamentals Individual Project.