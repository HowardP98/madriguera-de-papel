# 🎨 Library System - Frontend UI

Modern and reactive user interface for library management, designed to provide a smooth, fast, and scalable experience.

---

## 🛠️ Tech Stack

* **Vue.js 3**: Progressive framework for building reactive user interfaces.
* **Tailwind CSS**: Utility-first framework for modern and responsive design.
* **Vite**: Ultra-fast frontend build tool.
* **Axios**: HTTP client for consuming the backend REST API.

---

## 🚀 Setup & Running

### 🔧 Prerequisites

* Node.js (version 18 or higher recommended)
* npm or yarn

---

### 📥 Installation

1. Navigate to the frontend folder and install dependencies:

```bash
npm install
```

2. Set up the API connection by creating a `.env` file in the frontend root:

```env
VITE_API_BASE_URL=http://localhost:8080/api
```

3. Start the development server:

```bash
npm run dev
```

4. The app will be available at:

```
http://localhost:5173
```

---

### 🏗️ Production Build

To generate the optimized production build:

```bash
npm run build
```

To preview the production build:

```bash
npm run preview
```

---

## 📦 Project Structure

```
frontend/
 ├── .vscode/              # Editor configuration
 ├── public/               # Static files
 ├── src/
 │   ├── assets/           # Resources (images, styles)
 │   ├── components/       # Reusable components
 │   ├── views/            # Main views/pages
 │   ├── router/           # Routing configuration
 │   ├── services/         # API calls (Axios)
 │   ├── store/ (optional) # Global state management
 │   ├── App.vue           # Root component
 │   └── main.ts           # Entry point
 ├── .env.example          # Environment variables example
 ├── .gitignore
 ├── Dockerfile            # Frontend container setup
 ├── index.html            # Main HTML file
 ├── package.json
 ├── package-lock.json
 ├── tsconfig.json
 ├── tsconfig.app.json
 ├── tsconfig.node.json
 ├── vite.config.ts
 └── README.md
```

---

## 🔌 Backend Integration

The frontend communicates with the backend REST API using Axios.

Basic configuration example:

```javascript
import axios from "axios";

const api = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL,
});

export default api;
```

---

## 📚 Main Features

* 📖 Book catalog visualization
* ➕ Create and update books
* ❌ Delete records
* 👤 User management
* 🔄 Loan management
* 🔍 Real-time search and filtering

---

## 🎨 UI / UX

* Fully responsive design (mobile & desktop)
* Reusable component architecture
* Clean UI powered by Tailwind utilities
* SPA navigation (Single Page Application)

---

## 🧪 Testing (Optional)

If you add testing (e.g., with Vitest):

```bash
npm run test
```

---

## 🌍 Environment Variables

Create a `.env` file based on `.env.example`:

```bash
cp .env.example .env
```

Example:

```env
VITE_API_BASE_URL=http://localhost:8080/api
```

---

## 🐳 Docker Support

The project includes a `Dockerfile` for containerized deployment.

Example:

```bash
docker build -t library-frontend .
docker run -p 5173:5173 library-frontend
```

---

## 📌 Notes

* Make sure the backend is running before using the frontend.
* You can adjust the API URL depending on the environment (dev/prod).
* Suitable for deployment on platforms like Vercel, Netlify, or Docker-based environments.

---

## 👨‍💻 Author

Developed as part of a Library Management System (Frontend UI).
