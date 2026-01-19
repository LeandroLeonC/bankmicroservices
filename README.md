# bankmicroservices
microservices to test 
# Bank Microservices System

This project is a **microservices-based banking system** built with **Spring Boot**, **Java 17**, **Docker**, and **PostgreSQL**.  
It demonstrates clean architecture principles, service-to-service communication, and real-world backend patterns.

---

## 🧩 Architecture Overview

The system is composed of independent microservices that communicate over HTTP:

- **Customer Service** – Manages customer personal data
- **Account Service** – Manages bank accounts and transactions
- **PostgreSQL** – Dedicated database container
- **Docker Compose** – Service orchestration

Each microservice:
- Has its own database schema
- Exposes RESTful APIs
- Uses Spring Data JPA and Hibernate
- Is containerized and independently deployable

---

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot 3**
- **Spring Web**
- **Spring Data JPA**
- **Hibernate**
- **PostgreSQL**
- **Docker & Docker Compose**
- **Maven**

---

## 🚀 Services

### 🧑 Customer Service

Responsible for customer management.

**Port**
```http
8081
