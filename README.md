# 🛡️ User Authentication System (Java EE)

A complete, secure backend authentication system built using **Java Servlets**, **JSP**, and **JDBC**. This project demonstrates a production-ready approach to user management, focusing on security best practices and clean architecture.

## 🚀 Key Features
* **Secure Registration & Login:** Full user lifecycle management.
* **Password Hashing:** Uses **BCrypt** to ensure user passwords are never stored in plain text (Security First!).
* **Authentication Filter:** A custom `AuthFilter` that prevents unauthorized users from accessing the Dashboard.
* **DAO Design Pattern:** Decouples the database logic from the business logic for better maintainability.
* **Session Management:** Securely handles user sessions to track login states.

## 🛠️ Tech Stack
* **Language:** Java (JDK 8+)
* **Web Framework:** Java Servlets & Jakarta EE
* **Database:** MySQL 
* **Connection:** JDBC (Java Database Connectivity)
* **Frontend:** HTML5, CSS3
* **Tools:** Eclipse IDE, Apache Tomcat 10.1

## 📂 Project Architecture
The project follows a modular package structure:
* `dao`: Contains the `UserDAO` interface and `UserDAOImpl` for database CRUD operations.
* `model`: The `User` POJO representing the entity.
* `servlet`: Controllers handling HTTP requests (Login, Register, Logout).
* `filter`: Security layer to intercept and validate requests.
* `util`: Helper classes for `DBConnection` and `PasswordUtil`.

## ⚙️ Setup Instructions
1. **Database Setup:**
   * Create a MySQL database named `user_db`.
   * Create a `users` table with fields: `id`, `username`, `email`, and `password`.
2. **Configuration:**
   * Update the `db.properties` file in `src/main/resources` with your MySQL username and password.
3. **Deployment:**
   * Import the project into Eclipse as a "Dynamic Web Project."
   * Add your Tomcat Server in the "Servers" tab.
   * Right-click the project > **Run As** > **Run on Server**.
