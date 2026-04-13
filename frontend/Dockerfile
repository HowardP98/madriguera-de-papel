# ===== Build stage =====
FROM node:20-alpine AS builder

WORKDIR /app

COPY package*.json ./
RUN npm install

COPY . .

RUN npm run build

# ===== Production stage =====
FROM nginx:alpine

# Copiamos build de Vite
COPY --from=builder /app/dist /usr/share/nginx/html

# Exponemos puerto
EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
