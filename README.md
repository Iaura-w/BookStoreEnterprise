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
1. Start the application with Tomcat::
- Navigate to the db directory and start the services using Docker: ```cd db;
  docker-compose up```
2. Access the Application:
- The application will be available at: ```http://localhost:8080/bookStore```
3. Sample login cridentials:
  - As user: login: user, password: user
  - As admin: login: admin, password: admin
 
## Screenshots
1. User interface:
- Books view:
  
   <img src="https://github.com/user-attachments/assets/060460bd-f5e2-44d9-a913-240f3a659f2d" width="800">
- Cart view:

   <img src="https://github.com/user-attachments/assets/08df4b87-4984-41cf-bf16-ef4b33be7d78" width="800">
- Order details:

   <img src="https://github.com/user-attachments/assets/d186b88c-9811-472f-831c-96a2f95db9f9" width=800>
- PayU page:

   <img src="https://github.com/user-attachments/assets/01ce6ee3-78c5-4b16-990f-85abb035ca01" width=800>
- Order view after successfull payment:

  <img src="https://github.com/user-attachments/assets/142e6e36-8f6b-48a4-8b36-fa33c6915234" width=800>
2. Admin interface:
- Books view:

  <img src="https://github.com/user-attachments/assets/6c9e3f03-867c-40cd-8ace-c2528de0b057" width=800>

- Orders view:
  
  <img src="https://github.com/user-attachments/assets/5f28bc15-a453-47c9-ab2c-e256242e1cd7" width=800>




