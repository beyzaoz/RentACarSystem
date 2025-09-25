# 🚘 Vehicle Rental System

This project is a **console-based vehicle rental system** built with **Java 21** and **PostgreSQL 16**.  
It supports **authentication, role-based access, vehicle management, rentals, deposits, and transaction safety**.

---

## 📌 Functional Requirements

### 🔑 Authentication
- Login/Register with **email + password**
- Passwords stored with **SHA-256 (or similar) hashing**

### 👥 Roles & Authorization
- **ADMIN**: Add / Update / Delete / List vehicles (CRUD)
- **CUSTOMER** (Individual / Corporate): Search, Rent, Cancel rentals
- Only logged-in users can start a rental

### 🚗 Vehicles & Pricing
- Vehicle types: **Car, Helicopter, Motorcycle**
- Vehicle types modeled with a common **contract (interface/abstract class)**
- Separate pricing policy for each vehicle type
- Rental options: **Hourly / Daily / Weekly / Monthly**
- Price calculated based on duration

### 🔍 Search, Filter & Pagination
- Filter by **type, brand, price range**
- **Pagination** support for search results

### 📜 Business Rules
- Corporate accounts: **minimum 1-month rental**
- If vehicle value > **2,000,000 TL** → Renter must be **≥ 30 years old** and pay **10% deposit**

### ✅ Availability Check
- Prevent overlapping rentals for the same vehicle in a given date/time range

### 📂 Rental Records
- Users can view **current and past rentals**

### 💰 Deposit Lifecycle
- Deposit is calculated and stored
- Correct handling of **refunds/deductions** in cancel/complete flows

### ⚠️ Error Messages
- User-friendly console messages for **success** and **failure** states

---

## ⚙️ Technical Requirements

### 🖥️ Platform & Dependencies
- **Java 21**
- **PostgreSQL (preferably v16)**
- **JDBC** (via Maven or manual driver setup)

### 🗄️ Data Model & SQL
- Correct relational schema design
- Efficient use of **JOINs**

### 🔄 Transaction Management
- Rental creation / cancellation / deposit update must run in a **single transaction**
- Proper **commit/rollback** handling
- Deposit calculation, refund, deduction managed consistently

### 🌱 Seed Data
- 3 users: **ADMIN / CORPORATE / INDIVIDUAL**
- At least **6 vehicles**

### ⚡ Exception Handling
- **Expected errors** (invalid input) and **unexpected errors** (DB connection loss) should be handled gracefully

---

## 🏗️ Architecture & Code Quality
- **Layered architecture** (DAO/Repository, Service, Model/Entity, RentACarMain, UI)
- Clean, consistent formatting and indentation
- Java standard naming conventions:
    - Classes → `PascalCase`
    - Methods/Variables → `camelCase`
    - Constants → `UPPER_SNAKE_CASE`
- Clear comments and a descriptive **README**  