package com.nebulaide.controlplane.exception;

import com.nebulaide.sharedcontracts.response.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public ResponseEntity<ApiResponse<ProjectResponseDTO>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
//
//        String constraintName = extractConstraintName(ex);
//
//        if ("uk_project_project_name".equals(constraintName)) {
//            return ResponseEntity.status(HttpStatus.CONFLICT)
//                    .body(
//                        ApiResponse.<ProjectResponseDTO>builder()
//                            .message("A project with this name already exists. Please choose a different name.")
//                            .build()
//                    );
//        }
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .body(
//                    ApiResponse.<ProjectResponseDTO>builder()
//                        .message("The request violates a data rule. Please check your input.")
//                        .build()
//                );
//    }
//
//    private String extractConstraintName(Throwable exception) {
//        Throwable current = exception;
//
//        while (current != null) {
//            log.debug("Check the instance type: ", current instanceof org.hibernate.exception.ConstraintViolationException);
//            if (current instanceof org.hibernate.exception.ConstraintViolationException constraintException) {
//                return constraintException.getConstraintName();
//            }
//
//            current = current.getCause();
//        }
//
//        return null;
//    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiError> handleDuplicateResourceException(DuplicateResourceException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                        ApiError.builder()
                                .code("DUPLICATE_RESOURCE")
                                .message(ex.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                fieldErrors.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ApiError.builder()
                                .code("VALIDATION_ERROR")
                                .message("Validation Failed")
                                .errors(fieldErrors)
                                .build()
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ApiError.builder()
                                .code("INTERNAL_SERVER_ERROR")
                                .message("Unexpected Error Occurred")
                                .build()
                );
    }
}
