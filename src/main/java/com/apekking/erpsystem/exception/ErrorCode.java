package com.apekking.erpsystem.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    // ===== COMMON =====
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "Validation error"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"),

    // ===== COMPANY =====
    COMPANY_NOT_FOUND(HttpStatus.NOT_FOUND, "Company not found"),
    COMPANY_CODE_EXISTS(HttpStatus.BAD_REQUEST, "Company code already exists"),

    // ===== BRANCH =====
    BRANCH_NOT_FOUND(HttpStatus.NOT_FOUND, "Branch not found"),
    BRANCH_CODE_EXISTS(HttpStatus.BAD_REQUEST, "Branch code already exists for this company"),

    // ===== DEPARTMENT =====
    DEPARTMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "Department not found"),
    DEPARTMENT_CODE_EXISTS(HttpStatus.BAD_REQUEST, "Department code already exists in this branch"),
    INVALID_PARENT_DEPARTMENT(HttpStatus.BAD_REQUEST, "Invalid parent department"),

    // ===== USER =====
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found"),
    USERNAME_EXISTS(HttpStatus.BAD_REQUEST, "Username already exists"),
    EMAIL_EXISTS(HttpStatus.BAD_REQUEST, "Email already exists"),

    // ===== ROLE =====
    ROLE_NOT_FOUND(HttpStatus.NOT_FOUND, "Role not found"),
    ROLE_CODE_EXISTS(HttpStatus.BAD_REQUEST, "Role code already exists"),

    // ===== PERMISSION =====
    PERMISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "Permission not found"),
    PERMISSION_CODE_EXISTS(HttpStatus.BAD_REQUEST, "Permission code already exists"),

    // ===== AUTH =====
    INVALID_CREDENTIALS(HttpStatus.NOT_FOUND, "Invalid credentials"),
    USER_INACTIVE(HttpStatus.NOT_FOUND, "User inactive"),;


    private final HttpStatus httpStatus;
    private final String defaultMessage;

    ErrorCode(HttpStatus httpStatus, String defaultMessage) {
        this.httpStatus = httpStatus;
        this.defaultMessage = defaultMessage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }
}

