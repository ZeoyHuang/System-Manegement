# System_Management

## Introduction
This project is a powerful Java + Spring Boot backend system designed to provide a basic set of functionality for web applications. It covers all aspects of backend development, from data preparation to system monitoring. Key features include database schema creation and initialization, project setup and modularization, and generic configuration for global exception handling and consistent response. The system also provides rate limiting via AOP annotations, log management with detailed exception tracking, and secure token-based user authentication and authorization. In addition, the system has menu management, department and dictionary management, user and role management, task scheduling, online user monitoring, and log management. The project aims to provide a solid foundation for the development of secure, feature-rich web applications through an efficient and scalable back-end infrastructure.

## Software Architecture
- Representation Layer: This layer handles HTTP requests and responses. It includes the REST API controller that handles incoming requests and returns responses to the client.
- Service Layer: The service layer contains the business logic of the application. It coordinates data processing and communicates with the data access layer. The services are responsible for implementing the core functionality of the application, such as user management, authentication, and data processing.
- Data Access Layer: This layer interacts with the database (MySQL in this case). It consists of repositories or DAOs (Data Access Objects) that are responsible for querying and manipulating data.Spring Data JPA can be used to simplify the database interaction.

## Spring Data JPA can be used to simplify database interaction.

### Data Preparation
- Before you begin, make sure you have a MySQL database available.
- Execute the provided SQL script `systemmanagement.sql` to create the necessary database schema and tables.

### Project Initialization
- Open the Spring Boot project using IntelliJ IDEA or a similar IDE.
- Review the modular structure of the project, including the common, logging, system and other module tools.

### Configuration
#### 0. Global Configuration
- Explore and configure global settings in the `application.properties` or file as needed, including database connection details, Redis configuration, and other environment-specific settings.

#### 1. General Configuration
- Customized global exception class implementation for global exception handling.
- Customized generic return classes for consistent response.
- Tool utils Creation, including Rsa, Security, string handling, paging, file and Redis tools.
- Configuration packages for `Redis`, `Swagger` created.

#### 2. Logging Configuration
- In the system module resource, complete the `logback.xml` configuration.

#### 3. Security Configuration
- Implement the `springSecurityConfig` class for Spring Security configuration.
- Configure password encryption methods to protect static resources.
- Describes token-based authentication and permission checking.
- Explanation Annotation @PreAuthorize grants access to specific interfaces.

### Run the application:
- Start the Spring Boot application by running the main boot class.
- Ensure that all required dependencies are included and properly configured.

## Main Features

1. Security:
- Use Spring Security for user authentication and authorization.
- Secure user login, logout, and session management.
- Access to protected resources is controlled with fine-grained permissions. 2.

2. rate limiting:
- Implement rate limiting on API endpoints to manage request traffic. 3.

3. logging and monitoring:
- Configure logging of operations and exception tracking.
- Monitor system resources and online user status.

4. Captcha Generation:
- Generate and validate authentication codes for enhanced security.

5. user and role management:
- Manage users and roles using CRUD operations.
- Assign and manage role privileges. 6.

6. menus and access control:
- Manage menus, including creation, modification, and access control based on user rights. 7.

7. department and dictionary management:
- Manage departments and dictionaries through CRUD operations.
- Handle data export and paging.

8. Task Scheduling:
- Use Quartz to schedule and manage tasks and background jobs. 9.

9. File Storage:
- Manage local file uploads, queries, and data exports. 10.

10. online user management:
- Monitor and manage online user sessions.

11. log management:
- Collect and manage operation and exception logs.
- Retrieve and view log details.

## API
#### Captcha Generation API

- Explains how CAPTCHA generation works with Redis.
- Describe the process of generating and storing CAPTCHAs in Redis.

#### Login Authorization API

- Provides detailed information about how user login authorization works.
- Includes steps for credential verification, password decryption, and token generation.
- Explains using Redis for user online status management.
- Describes the structure of the response data, including user information and tokens.


#### User Information API
- Documents how to retrieve user information using the Security Tools class.

#### Logout API
- Explains the process of logging out and deleting online user data in Redis.

#### MenuController API

- Describe the implementation of menu CRUD operations.
- Explain how the current user's permissions are used to determine which menu to display.
- Detailed methods for obtaining sub-menu IDs and sibling/superior menu data.


#### Department and Dictionary Management API (DeptController, DictController, DictDetailController)

- Document CRUD operations for departments and dictionaries.
- Discusses data export and paging considerations.


#### User and Role Management API (UserController, RoleController)

- Describe CRUD operations for users and roles, including data export.
- Explain role permission assignments.
- Defines user role relationships.


#### Task Scheduling API (QuartzJobController)

- Explains how to manage scheduled tasks, including adding, deleting, viewing, and executing tasks.
- Discuss the importance of thread pooling for task execution.
- Online User and Service Monitoring (OnlineController and MonitorController)
- Document online user management, including querying, exporting, and forced logout.
- Explains how service monitoring retrieves system information, CPU, memory, disk, and time data.

#### Log Management API (LogController)
- Describes the management of operation logs and exception logs, including querying, exporting, and viewing details.

#### System Tools API (LocalStorageController)
- Describes file storage management functions, including local file uploading, querying, paging, and data exporting.
