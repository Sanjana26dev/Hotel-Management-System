# 🏨 Hotel Management System — Spring Boot Microservices

A full-featured **Hotel Management System** built as a Spring Boot REST API microservices project.  
Each domain is an independent Spring Boot service backed by a shared MySQL database.

---

## Architecture

```
┌─────────────────────────────────────────────────────────┐
│                  Client (REST / Frontend)                │
└──────────┬──────────┬──────────┬──────────┬─────────────┘
           │          │          │          │
    ┌──────▼─┐  ┌─────▼──┐ ┌────▼───┐ ┌────▼────┐ ┌───────────┐
    │  auth  │  │  room  │ │customer│ │employee │ │  driver   │
    │:8081   │  │ :8082  │ │ :8083  │ │ :8084   │ │  :8085    │
    └────────┘  └────────┘ └────────┘ └─────────┘ └───────────┘
           │          │          │          │            │
           └──────────┴──────────┴──────────┴────────────┘
                              MySQL (hms)
```

### Microservices

| Service           | Port | Responsibility                        |
|-------------------|------|---------------------------------------|
| `auth-service`    | 8081 | Login, JWT token issuance, registration |
| `room-service`    | 8082 | Room CRUD, availability & clean-status |
| `customer-service`| 8083 | Check-in / check-out, customer CRUD   |
| `employee-service`| 8084 | Employee CRUD, filter by job title    |
| `driver-service`  | 8085 | Driver CRUD, pickup management        |

---

## Technology Stack

- **Java 17**
- **Spring Boot 3.2**
- **Spring Data JPA** (Hibernate)
- **Spring Security** + **JWT** (auth-service)
- **MySQL 8**
- **Lombok**
- **Maven** (multi-module)

---

## Prerequisites

| Tool        | Version |
|-------------|---------|
| JDK         | 17+     |
| Maven       | 3.9+    |
| MySQL       | 8.0+    |

---

## Quick Start

### 1. Create the Database

```bash
mysql -u root -p < schema.sql
```

### 2. Configure Credentials

Edit `application.properties` in each service (`src/main/resources/`) and set:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/hms
spring.datasource.username=<your-user>
spring.datasource.password=<your-password>
```

### 3. Build All Services

```bash
mvn clean install
```

### 4. Run Each Service

```bash
# In separate terminals (or use your IDE)
cd auth-service     && mvn spring-boot:run
cd room-service     && mvn spring-boot:run
cd customer-service && mvn spring-boot:run
cd employee-service && mvn spring-boot:run
cd driver-service   && mvn spring-boot:run
```

---

## API Reference

### Auth Service — `http://localhost:8081`

| Method | Path                     | Description          |
|--------|--------------------------|----------------------|
| POST   | `/api/auth/register`     | Register a new user  |
| POST   | `/api/auth/login`        | Login, returns JWT   |

**Login request:**
```json
{ "username": "admin", "password": "secret" }
```
**Login response:**
```json
{ "token": "eyJ...", "username": "admin", "role": "STAFF", "expiresIn": 86400000 }
```

---

### Room Service — `http://localhost:8082`

| Method | Path                                          | Description               |
|--------|-----------------------------------------------|---------------------------|
| GET    | `/api/rooms`                                  | List all rooms            |
| GET    | `/api/rooms?bedType=Double&availableOnly=true` | Filter rooms              |
| GET    | `/api/rooms/{id}`                             | Get room by ID            |
| GET    | `/api/rooms/number/{roomNumber}`              | Get room by room number   |
| POST   | `/api/rooms`                                  | Add a new room            |
| PUT    | `/api/rooms/{id}`                             | Update room               |
| PATCH  | `/api/rooms/number/{roomNumber}/availability` | Update availability       |
| PATCH  | `/api/rooms/number/{roomNumber}/clean-status` | Update clean status       |
| DELETE | `/api/rooms/{id}`                             | Delete room               |

---

### Customer Service — `http://localhost:8083`

| Method | Path                              | Description              |
|--------|-----------------------------------|--------------------------|
| GET    | `/api/customers`                  | List all customers       |
| GET    | `/api/customers?roomNumber=101`   | Customers in a room      |
| GET    | `/api/customers/{id}`             | Get customer by ID       |
| POST   | `/api/customers/check-in`         | New customer check-in    |
| PATCH  | `/api/customers/{id}/check-details`| Update check-in details |
| PATCH  | `/api/customers/{id}/check-out`   | Check out customer       |
| DELETE | `/api/customers/{id}`             | Delete customer record   |

---

### Employee Service — `http://localhost:8084`

| Method | Path                              | Description              |
|--------|-----------------------------------|--------------------------|
| GET    | `/api/employees`                  | List all employees       |
| GET    | `/api/employees?job=Manager`      | Filter by job title      |
| GET    | `/api/employees/{id}`             | Get employee by ID       |
| GET    | `/api/employees/search?keyword=chef` | Search by job keyword |
| POST   | `/api/employees`                  | Add new employee         |
| PUT    | `/api/employees/{id}`             | Update employee          |
| DELETE | `/api/employees/{id}`             | Delete employee          |

---

### Driver Service — `http://localhost:8085`

| Method | Path                                  | Description              |
|--------|---------------------------------------|--------------------------|
| GET    | `/api/drivers`                        | List all drivers         |
| GET    | `/api/drivers?brand=Toyota`           | Filter by vehicle brand  |
| GET    | `/api/drivers?availableOnly=true`     | Available drivers only   |
| GET    | `/api/drivers/{id}`                   | Get driver by ID         |
| POST   | `/api/drivers`                        | Add new driver           |
| PUT    | `/api/drivers/{id}`                   | Update driver            |
| PATCH  | `/api/drivers/{id}/availability`      | Update availability      |
| DELETE | `/api/drivers/{id}`                   | Delete driver            |

---

## Project Structure

```
hotel-management-system/
├── pom.xml                   ← Parent POM (multi-module)
├── schema.sql                ← MySQL DDL script
├── auth-service/             ← Port 8081
├── room-service/             ← Port 8082
├── customer-service/         ← Port 8083
├── employee-service/         ← Port 8084
├── driver-service/           ← Port 8085
└── legacy/                   ← Original Swing desktop app (reference)
```

Each service follows the same layered architecture:
```
src/main/java/com/hotel/<domain>/
├── <Domain>ServiceApplication.java
├── controller/
├── service/
├── repository/
├── model/
└── dto/
```

---

## Legacy Application

The original Java Swing desktop application is preserved in the `legacy/` directory for reference.
