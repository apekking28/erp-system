# ERP System Backend

ERP System Backend is a **Spring Boot 3** backend application for an **ERP / SaaS system**.  
Designed with **multi-tenant architecture**, **JWT authentication**, and **role & permission–based authorization**.

The project focuses on **security**, **scalability**, and **clean backend architecture**, and is suitable as an ERP foundation or backend engineering portfolio.

---

## Tech Stack
- Java 17
- Spring Boot 3
- Spring Security
- JWT (JSON Web Token)
- JPA / Hibernate
- MySQL / MariaDB
- Maven

---

## Core Features

### Authentication
- Stateless JWT authentication
- Login with username or email
- Password hashing with BCrypt
- JWT payload contains:
  - userId
  - companyId
  - branchId
  - roles
  - permissions

---

### Authorization
- Role-Based Access Control (RBAC)
- Permission-based authorization
- Annotation-based security:
  - `@PreAuthorize("hasRole('ADMIN')")`
  - `@PreAuthorize("hasAuthority('USER_CREATE')")`

---

### Multi-Tenant Core Master
- **Company**
  - Main tenant boundary
  - Active / inactive status
  - Soft delete
- **Branch**
  - Always linked to a company
  - Active / inactive
  - Soft delete
- **Department**
  - Company & branch scoped
  - Hierarchical (parent-child)

---

### User Management
- Users belong to a company (branch optional)
- User status: active, inactive, soft deleted
- Role assignment & password reset

---

### Role & Permission
- Company-scoped roles
- Global permissions
- Many-to-many mapping:
  - `user_roles`
  - `role_permissions`
- Role codes are immutable

---

### Security Design
- Fully stateless JWT security
- Custom JWT filter & principal
- Prevents cross-company and cross-branch access

---

### Database Design
- Relational database
- Soft delete for all master data
- Audit fields:
  - created_at, created_by
  - updated_at, updated_by

Core tables:
`companies`, `branches`, `departments`, `users`, `roles`, `permissions`, `user_roles`, `role_permissions`

---

## Modules Under Development
- Attendance (check-in/out, shifts)
- Payroll (salary, deductions, monthly payroll)
- HR (employee & position management)
- Finance, Inventory, Reporting

---

## Run the Project
```bash
git clone https://github.com/your-username/erp-system-backend.git
cd erp-system-backend
mvn spring-boot:run
