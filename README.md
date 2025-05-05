# University Management System (KBI Learning Project)

## Project Overview

This project is a **learning resource** created for KBI students to understand how to build a **Java RESTful API** using **Spring Boot**. The system is designed with **Clean Architecture** principles and utilizes **PostgreSQL** for database management. It includes two databases: **Jurusan** (Department) and **Mahasiswa** (Student). This project demonstrates the use of **JPA** (Java Persistence API) and **JDBC** (Java Database Connectivity) for data management.

### Technologies Used
- **Spring Boot**: Java-based framework for building RESTful APIs
- **Clean Architecture**: A method of organizing your codebase for maintainability and scalability
- **PostgreSQL**: Relational database used to store data
- **Swagger**: Tool for documenting and testing APIs
- **JPA (Java Persistence API)**: A framework for object-relational mapping (ORM)
- **JDBC (Java Database Connectivity)**: Used for direct interaction with the PostgreSQL database

---

## Project Structure

The project follows the **Clean Architecture** structure, which divides the application into layers for better maintainability. The layers include:

1. **Controller**: Handles incoming HTTP requests and maps them to service methods.
2. **Service**: Contains business logic and interacts with repositories.
3. **Repository**: Interface between the application and the database.
4. **Model**: Represents the core entities (such as `Jurusan` and `Mahasiswa`) and their relationships.
5. **DTO**: Represents the output entities for Frontend (such as `Jurusan` and `Mahasiswa`).

---

## Database Structure

- **Jurusan**: Represents academic departments in the university.
  - Fields: id, name, description, etc.

- **Mahasiswa**: Represents students in the university.
  - Fields: id, name, department_id (foreign key to `Jurusan`), etc.

---

## API Documentation (Swagger)

This project integrates **Swagger** to provide an interactive documentation interface for the API. Once the application is running, you can access the API documentation at:


For more details, check out the Swagger UI for interactive API testing http://localhost:8080/swagger-ui/index.html.

Contributing
Feel free to contribute by opening issues or submitting pull requests. This project is open for anyone who wants to learn and improve.

License
This project is licensed under the MIT License - see the LICENSE file for details.
