# 📚 Online Library System (Madriguera de Papel)

> Full-stack web application for managing an online library, featuring a robust **Java 21 + Spring Boot backend** and a modern **Vue.js 3 frontend** styled with Tailwind CSS.

![Java Library System CI](https://github.com/HowardP98/online-library-system/actions/workflows/maven.yml/badge.svg)

---

## 🚀 Live Overview

* 📦 Backend API: `http://localhost:8080`
* 🎨 Frontend UI: `http://localhost:5173`
* 📄 API Docs (Swagger): `http://localhost:8080/swagger-ui.html`

---

## 🛠️ Technology Stack

### 🎨 Frontend

* Vue.js 3
* TypeScript
* Tailwind CSS
* Vite
* Axios

### ⚙️ Backend

* Java 21
* Spring Boot
* Spring Data JPA
* RESTful APIs
* SpringDoc OpenAPI (Swagger UI)
* MySQL (Dockerized)

---

## 🧩 Project Structure

```bash
online-library-system/
 ├── backend/            # Spring Boot API
 ├── frontend/           # Vue 3 + Tailwind UI
 ├── docker-compose.yml  # Full-stack container orchestration
 ├── .gitignore          # Version control exclusion rules
 ├── LICENSE             # MIT License
 └── README.md           # Main documentation
```

---

## ✨ Key Features

* 📚 Complete book catalog management (CRUD)
* 👤 User management system
* 🔄 Loan/return system
* 🎨 Modern, responsive UI (mobile-first)
* 📄 Interactive API documentation (Swagger UI)
* ⚡ Fast development with Vite
* 🗄️ Preloaded demo data for testing

---

## ⚙️ Getting Started

Follow these instructions to run the project locally.

---

### 🔧 Prerequisites

* Node.js + npm
* Java JDK 21
* Maven
* Docker

---

### 📥 Installation

#### 1. Clone the repository

```bash
git clone https://github.com/HowardP98/online-library-system.git
cd online-library-system
```

---

### 🐳 2. Database Setup (Docker)

Run a MySQL container:

```bash
docker run --name library-db \
-e MYSQL_ROOT_PASSWORD=root \
-e MYSQL_DATABASE=library \
-p 3307:3306 \
-d mysql:latest
```

> ⚠️ Make sure your backend is configured to use port **3307**

---

### ⚙️ 3. Backend Setup

```bash
cd backend
mvn spring-boot:run
```

🔗 Swagger UI available at:
`http://localhost:8080/swagger-ui.html`

---

### 🎨 4. Frontend Setup

```bash
cd frontend
npm install
npm run dev
```

🌐 App running at:
`http://localhost:5173`

---

## 🔌 Architecture Overview

```
[ Vue.js Frontend ]
          │
          ▼
[ Spring Boot REST API ]
          │
          ▼
[ MySQL Database (Docker) ]
```

---

## 📄 API Documentation

The API is fully documented using **Swagger UI**:

```
http://localhost:8080/swagger-ui.html
```

---

## 🧪 Future Improvements

* 🔐 JWT Authentication & Security
* 🌍 Deployment (Docker Compose / Cloud)
* 🧪 Unit & Integration Testing
* 📊 Admin dashboard with analytics
* 📦 CI/CD pipeline

---

## 🤝 Contributing

Contributions are welcome! Feel free to fork the repo and submit a PR.

---

## 📝 License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## 👨‍💻 Author

Developed by **HowardP98** as a full-stack portfolio project.
