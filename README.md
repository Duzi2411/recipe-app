# Recipe Management System

A web-based Recipe Management System built with Java and Spring Boot. The application allows users to create, organize, and manage recipes, ingredients, categories, and meal plans through a user-friendly web interface.

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
- Password hashing using BCrypt
- Server-side form validation
- Custom exception handling
- CRUD operations for recipes
- CRUD operations for categories
- CRUD operations for ingredients
- CRUD operations for meal plans

## Main Functionalities

### Recipe Management
- Create new recipes
- View recipe details
- Edit existing recipes
- Delete recipes
- Assign recipes to categorize

### Category Management
- Create categories
- View all categories
- Edit category information
- Delete categories

### Ingredient Management
- Add ingredients
- View ingredients
- Edit ingredient information
- Delete ingredients

### Meal Planning
- Create meal plans
- View planned meals
- Edit meal plans
- Delete meal plans
- Associate meal plans with recipes

## Authentication and Authorization

The application uses session-based authentication.

- Guests can access public pages.
- Registered users can access the available user functionalities.
- Administrators can manage categories and ingredients.
- Access to protected pages is controlled according to user roles.

Passwords are securely hashed using BCrypt.

## Application Architecture

The application follows a layered architecture:

- **Entity** – JPA entities representing application data.
- **Repository** – Interfaces for database operations.
- **Service** – Business logic and data management.
- **Controller** – Handles HTTP requests and application navigation.
- **Config** – Application and security configuration.
- **Exception** – Custom exceptions and global exception handling.

## Database and Integrations

The application uses MySQL as its relational database.

The database is named:

`recipe_app`

Spring Data JPA is used for database access and entity persistence.

Configure your database connection in `src/main/resources/application.properties` with your local MySQL username and password.

## Running the Application

1. Clone the repository:

   `git clone https://github.com/Duzi2411/recipe-app.git`

2. Open the project in IntelliJ IDEA.

3. Make sure Java 21 and MySQL are installed.

4. Create the `recipe_app` database.

5. Configure the database connection in `application.properties`.

6. Run the application using Maven:

   `./mvnw spring-boot:run`

   On Windows:

   `mvnw.cmd spring-boot:run`

7. Open the application in your browser:

   `http://localhost:8080`

## Author

Recipe Management System – Spring Fundamentals Individual Project.