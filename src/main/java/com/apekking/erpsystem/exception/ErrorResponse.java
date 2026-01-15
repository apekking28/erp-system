package com.apekking.erpsystem.exception;

import java.time.LocalDateTime;

public class ErrorResponse {

    private String code;
    private String message;
    private LocalDateTime timestamp;

    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public static ErrorResponse unauthorized() {
        return new ErrorResponse(
                "UNAUTHORIZED",
                "Authentication is required to access this resource"
        );
    }

    public static ErrorResponse forbidden() {
        return new ErrorResponse(
                "FORBIDDEN",
                "You do not have permission to access this resource"
        );
    }

    public static ErrorResponse business(String code, String message) {
        return new ErrorResponse(code, message);
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}

