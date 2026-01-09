-- =====================================================
-- ERP SYSTEM - CORE MASTER DATA
-- Flyway Migration V1
-- Database: PostgreSQL
-- =====================================================

-- =========================
-- COMPANY
-- =========================
CREATE TABLE companies (
                           id              BIGSERIAL PRIMARY KEY,
                           code            VARCHAR(50) NOT NULL UNIQUE,
                           name            VARCHAR(150) NOT NULL,
                           status          VARCHAR(20) NOT NULL,
                           timezone        VARCHAR(50),
                           currency        VARCHAR(10),
                           is_deleted      BOOLEAN DEFAULT FALSE,

                           created_at      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           created_by      VARCHAR(100),
                           updated_at      TIMESTAMP,
                           updated_by      VARCHAR(100)
);

-- =========================
-- BRANCH
-- =========================
CREATE TABLE branches (
                          id              BIGSERIAL PRIMARY KEY,
                          company_id      BIGINT NOT NULL,
                          code            VARCHAR(50) NOT NULL,
                          name            VARCHAR(150) NOT NULL,
                          address         TEXT,
                          is_active       BOOLEAN DEFAULT TRUE,
                          is_deleted      BOOLEAN DEFAULT FALSE,

                          created_at      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          created_by      VARCHAR(100),
                          updated_at      TIMESTAMP,
                          updated_by      VARCHAR(100),

                          CONSTRAINT fk_branch_company
                              FOREIGN KEY (company_id) REFERENCES companies(id),

                          CONSTRAINT uq_branch_company_code
                              UNIQUE (company_id, code)
);

-- =========================
-- DEPARTMENT
-- =========================
CREATE TABLE departments (
                             id              BIGSERIAL PRIMARY KEY,
                             company_id      BIGINT NOT NULL,
                             branch_id       BIGINT NOT NULL,
                             code            VARCHAR(50) NOT NULL,
                             name            VARCHAR(150) NOT NULL,
                             parent_id       BIGINT,
                             is_deleted      BOOLEAN DEFAULT FALSE,

                             created_at      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                             created_by      VARCHAR(100),
                             updated_at      TIMESTAMP,
                             updated_by      VARCHAR(100),

                             CONSTRAINT fk_department_company
                                 FOREIGN KEY (company_id) REFERENCES companies(id),

                             CONSTRAINT fk_department_branch
                                 FOREIGN KEY (branch_id) REFERENCES branches(id),

                             CONSTRAINT fk_department_parent
                                 FOREIGN KEY (parent_id) REFERENCES departments(id),

                             CONSTRAINT uq_department_branch_code
                                 UNIQUE (branch_id, code)
);

-- =========================
-- USERS
-- =========================
CREATE TABLE users (
                       id              BIGSERIAL PRIMARY KEY,
                       company_id      BIGINT NOT NULL,
                       branch_id       BIGINT,
                       username        VARCHAR(100) NOT NULL UNIQUE,
                       email           VARCHAR(150) NOT NULL UNIQUE,
                       password        VARCHAR(255) NOT NULL,
                       last_login      TIMESTAMP,
                       is_active       BOOLEAN DEFAULT TRUE,
                       is_deleted      BOOLEAN DEFAULT FALSE,

                       created_at      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       created_by      VARCHAR(100),
                       updated_at      TIMESTAMP,
                       updated_by      VARCHAR(100),

                       CONSTRAINT fk_user_company
                           FOREIGN KEY (company_id) REFERENCES companies(id),

                       CONSTRAINT fk_user_branch
                           FOREIGN KEY (branch_id) REFERENCES branches(id)
);

-- =========================
-- ROLES
-- =========================
CREATE TABLE roles (
                       id              BIGSERIAL PRIMARY KEY,
                       company_id      BIGINT NOT NULL,
                       code            VARCHAR(50) NOT NULL,
                       name            VARCHAR(100) NOT NULL,
                       description     VARCHAR(255),
                       is_deleted      BOOLEAN DEFAULT FALSE,

                       created_at      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       created_by      VARCHAR(100),
                       updated_at      TIMESTAMP,
                       updated_by      VARCHAR(100),

                       CONSTRAINT fk_role_company
                           FOREIGN KEY (company_id) REFERENCES companies(id),

                       CONSTRAINT uq_role_company_code
                           UNIQUE (company_id, code)
);

-- =========================
-- PERMISSIONS (GLOBAL)
-- =========================
CREATE TABLE permissions (
                             id              BIGSERIAL PRIMARY KEY,
                             code            VARCHAR(100) NOT NULL UNIQUE,
                             description     VARCHAR(255)
);

-- =========================
-- USER_ROLES (N-M)
-- =========================
CREATE TABLE user_roles (
                            user_id     BIGINT NOT NULL,
                            role_id     BIGINT NOT NULL,

                            CONSTRAINT pk_user_roles
                                PRIMARY KEY (user_id, role_id),

                            CONSTRAINT fk_user_roles_user
                                FOREIGN KEY (user_id) REFERENCES users(id),

                            CONSTRAINT fk_user_roles_role
                                FOREIGN KEY (role_id) REFERENCES roles(id)
);

-- =========================
-- ROLE_PERMISSIONS (N-M)
-- =========================
CREATE TABLE role_permissions (
                                  role_id         BIGINT NOT NULL,
                                  permission_id   BIGINT NOT NULL,

                                  CONSTRAINT pk_role_permissions
                                      PRIMARY KEY (role_id, permission_id),

                                  CONSTRAINT fk_role_permissions_role
                                      FOREIGN KEY (role_id) REFERENCES roles(id),

                                  CONSTRAINT fk_role_permissions_permission
                                      FOREIGN KEY (permission_id) REFERENCES permissions(id)
);

-- =====================================================
-- INDEXES (PERFORMANCE)
-- =====================================================
CREATE INDEX idx_branch_company_id ON branches(company_id);
CREATE INDEX idx_department_branch_id ON departments(branch_id);
CREATE INDEX idx_user_company_id ON users(company_id);
CREATE INDEX idx_role_company_id ON roles(company_id);
