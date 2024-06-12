# Customer Management Portal

This project is designed as a Customer Management System, offering robust functionalities to manage customer data efficiently. The application includes features for user authentication, CRUD operations on customer data, and syncing customer information from an external API.

# Technologies Used
**Backend**: Java Spring Boot  
**Frontend**: HTML, CSS, JavaScript  
**Database**: MySQL  
**Security**: Spring Security with JWT authentication

# Getting Started
To run the application locally, follow these steps:

1. Clone the repository to your local machine.
2. Set up your MySQL database and configure the database connection in the backend.
3. Navigate to the frontend directory and open the `index.html` file in your web browser to   access the login page.
4. Use the provided credentials to log in.
5. Once logged in, you can utilize all the features provided by the application.

# Features
- **User Authentication**: Users can log in with their credentials.
- **Customer Management**: Users can perform CRUD operations on customer details.
- **Sync Functionality**: Users can synchronize customer data from a remote API, updating the local database.
- **Search Functionality**: Users can search for customers based on various criteria such as first name, last name, email, etc.

## Setup
Ensure that MySQL is installed on your system. Update the `application.properties` file in the backend directory with your MySQL database credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/cmp?createTableIfNotExists=true
spring.datasource.username=root # your MySQL username
spring.datasource.password=password # your MySQL password
spring.jpa.hibernate.ddl-auto=update
```

## Running the Application
After setting up the database configuration, run the application. To access the frontend, open the `index.html` file in your web browser and log in using the provided credentials.
