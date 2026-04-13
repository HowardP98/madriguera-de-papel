# ⚙️ Library System - Backend API

This is the core logic of the library system, built with a focus on robustness, scalability, and modern Java best practices.

---

## 🛠️ Tech Stack

* **Java 21**: Leveraging the latest performance and language features.
* **Spring Boot**: Main framework for building the REST API.
* **Spring Data JPA**: Data persistence and interaction with MySQL.
* **Hibernate**: ORM engine for object-relational mapping.
* **Maven**: Dependency management and build tool.

---

## 🚀 Setup & Running

### 🔧 Prerequisites

* JDK 21 installed
* MySQL Server (or Docker container running MySQL)
* Maven installed (or use the included wrapper)

---

### 📥 Installation

1. Configure the database in `src/main/resources/application.properties` (or `application.yml`):

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/library_db
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

2. Build the project:

```bash
./mvnw clean install
```

(Or use `mvn clean install` if Maven is installed globally)

3. Run the application:

```bash
./mvnw spring-boot:run
```

4. The API will be available at:

```
http://localhost:8080
```

---

## 📦 Project Structure

```id="backend-structure"
backend/
 ├── .mvn/wrapper/                # Maven wrapper configuration
 ├── src/
 │   └── main/
 │       ├── java/com/diw/practica/
 │       │   ├── controller/      # REST controllers
 │       │   ├── service/         # Business logic
 │       │   ├── repository/      # Data access layer
 │       │   ├── model/           # JPA entities
 │       │   └── dto/             # Data transfer objects (if used)
 │       └── resources/
 │           ├── application.properties
 │           └── data.sql (optional)
 ├── .gitattributes
 ├── .gitignore
 ├── Dockerfile                   # Backend container setup
 ├── mvnw                         # Maven wrapper (Unix)
 ├── mvnw.cmd                     # Maven wrapper (Windows)
 ├── pom.xml                      # Maven configuration
 └── README.md
```

---

## 📚 Main Endpoints

### 📖 Books

| Method | Endpoint        | Description       |
| ------ | --------------- | ----------------- |
| GET    | /api/books      | Get all books     |
| GET    | /api/books/{id} | Get book by ID    |
| POST   | /api/books      | Create a new book |
| PUT    | /api/books/{id} | Update a book     |
| DELETE | /api/books/{id} | Delete a book     |

---

### 👤 Users

| Method | Endpoint        | Description    |
| ------ | --------------- | -------------- |
| GET    | /api/users      | Get all users  |
| GET    | /api/users/{id} | Get user by ID |
| POST   | /api/users      | Create a user  |
| DELETE | /api/users/{id} | Delete a user  |

---

### 🔄 Loans

| Method | Endpoint               | Description           |
| ------ | ---------------------- | --------------------- |
| GET    | /api/loans             | Get all loans         |
| POST   | /api/loans             | Create a loan         |
| PUT    | /api/loans/{id}/return | Mark loan as returned |

---

## 🧪 Testing

Run tests with:

```bash
./mvnw test
```

---

## 🌍 Environment Variables (Optional)

Avoid hardcoding credentials by using environment variables:

```properties
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
```

---

## 🐳 Docker (Optional)

Basic `docker-compose.yml` example for running MySQL:

```yaml
version: '3.8'
services:
  mysql:
    image: mysql:8
    container_name: library_mysql
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: library_db
    ports:
      - "3306:3306"
```

---

## 📌 Notes

* Use tools like **Postman** or **Insomnia** to test the API.
* SQL logging can be enabled for debugging purposes.
* The project is structured to scale towards microservices architecture.

---

## 👨‍💻 Author

Developed as part of a Library Management System (Backend API).
