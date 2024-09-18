# BookStoreEnterprise
BookStoreEnterprise is a web application for managing an online bookstore, providing an intuitive interface for both administrators and users. The application is built using Spring Boot and integrated with PayU for seamless payment processing.

## Features

1. Security (Spring Security)
   - Role-based access control (Admin and User roles)
   - User authentication and registration
2. CRUD operations
   - Admins can manage books (add, edit, delete) and orders (update order status)
   - Users can browse books, add them to the cart, and place orders
3. Shopping Cart
   - Users can add or remove books from the cart
   - Users can view the cart's contents
4. Orders Management
   - Users can place orders for books in the cart
   - Admins can view all orders and update their status
5. Payments
   - Integration with PayU payment gateway
   - Users can complete payments for their orders
  
## Technologies
   - Java: The primary programming language used in the application.
   - Spring Boot: Framework used for building the backend.
   - Spring Security: Used to handle user authentication and authorization.
   - MySQL: Relational database for storing book and order information.
   - PayU API: Integration for handling payment transactions.
   - Docker: For containerizing the database and running services.
   - Tomcat: Web server used to deploy and run the application.

## Getting Started
1. Database Setup:
- Navigate to the db directory and start the services using Docker: ```cd db;
  docker-compose up```
2. Start the application with Tomcat:
  - Windows: ```startup```
   - Linux: ```./startup.sh```
3. Access the Application:
- The application will be available at: ```http://localhost:8080/bookStore```
