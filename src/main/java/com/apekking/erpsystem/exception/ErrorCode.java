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
    BRANCH_CODE_EXISTS(HttpStatus.BAD_REQUEST, "Branch code already exists for this company");

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

