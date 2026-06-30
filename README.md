# Catch-Up 🛒

Catch-Up is a secure and scalable MultiVendor E-Commerce Backend application developed using Java Spring Boot. The system allows customers, sellers, and administrators to interact through dedicated REST APIs with JWT authentication and role-based authorization.

The project follows a layered architecture using Spring Boot, Spring Security, Hibernate, JPA, and MySQL. It provides complete backend functionality for a modern online shopping platform including product management, shopping cart, wishlist, checkout, orders, payments, reviews, seller dashboard, and admin management.

The APIs are fully documented using Swagger OpenAPI, making testing and integration simple and developer-friendly.

Features
## ✨ Features

### Authentication & Security
- JWT Authentication
- Spring Security
- Role-Based Authorization
- Password Encryption using BCrypt
- Protected APIs

### Customer Features
- User Registration & Login
- View Products
- Search Products
- Filter Products by Category
- Shopping Cart
- Wishlist
- Checkout
- Order History
- Payments
- Product Reviews

### Seller Features
- Seller Dashboard
- Add Products
- Update Products
- Delete Products
- View Seller Products
- View Seller Orders
- Update Order Status

### Admin Features
- View All Users
- View All Products
- View All Orders
- Update Order Status
- System Monitoring APIs

### Product Management
- CRUD Operations
- Product Categories
- Stock Management
- Seller Information

### Order Management
- Checkout Process
- Order Items
- Order Tracking
- Status Updates

### Payment Module
- Payment Creation
- Payment Status Update
- Order-wise Payment History

### Review System
- Add Review
- View Product Reviews
- Delete Review

### Wishlist
- Add to Wishlist
- Remove from Wishlist
- View Wishlist

### Shopping Cart
- Add to Cart
- Update Quantity
- Remove Items
- View Cart

### API Documentation
- Swagger OpenAPI Integration
Technologies Used
## 🛠 Technologies Used

### Backend
- Java 21
- Spring Boot 4
- Spring Security
- Spring Data JPA
- Hibernate ORM
- Maven

### Database
- MySQL 8

### Authentication
- JWT (JSON Web Token)
- BCrypt Password Encoder

### API Testing
- Swagger OpenAPI
- Postman

### Build Tool
- Maven

### Version Control
- Git
- GitHub
Project Architecture
## 📂 Project Structure

Controller
↓
Service
↓
Repository
↓
MySQL Database
Main Modules
## 📦 Modules

- Authentication
- User Management
- Product Management
- Cart Management
- Wishlist Management
- Checkout
- Order Management
- Payment Management
- Review Management
- Seller Dashboard
- Admin Dashboard
- Global Exception Handling
Security
## 🔐 Security

- JWT Authentication
- Role-Based Access Control
- BCrypt Password Encryption
- Protected REST APIs
- Custom JWT Filter
Swagger
## 📖 API Documentation

Swagger UI

http://localhost:8080/swagger-ui/index.html

OpenAPI JSON

http://localhost:8080/v3/api-docs
Database
## 💾 Database

MySQL Database

Database Name:

ecommerce_db
Future Enhancements
## 🚀 Future Enhancements

- React Frontend
- Image Upload
- Email Notifications
- PDF Invoice Generation
- Product Ratings Analytics
- Docker Deployment
- Cloud Deployment
