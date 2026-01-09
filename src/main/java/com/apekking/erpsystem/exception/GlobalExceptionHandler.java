package com.apekking.erpsystem.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ===============================
    // Business Exception
    // ===============================
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {

        ErrorCode code = ex.getErrorCode();

        return ResponseEntity
                .status(code.getHttpStatus())
                .body(new ErrorResponse(code.name(), code.getDefaultMessage()));
    }

    // ===============================
    // Validation Error (@Valid)
    // ===============================
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex) {

        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(
                        ErrorCode.VALIDATION_ERROR.name(),
                        "Invalid request data"
                ));
    }

    // ===============================
    // Fallback (Unexpected Error)
    // ===============================
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {

        return ResponseEntity
                .status(ErrorCode.INTERNAL_SERVER_ERROR.getHttpStatus())
                .body(new ErrorResponse(
                        ErrorCode.INTERNAL_SERVER_ERROR.name(),
                        ErrorCode.INTERNAL_SERVER_ERROR.getDefaultMessage()
                ));
    }
}

