package org.sky_pro.team_work.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestControllerAdvice
@Slf4j
public class RecommendationServiceExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleUserNotFoundException(UserNotFoundException re) {
        log.error(re.getMessage());
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("message", re.getMessage());
        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        if (ex.getRequiredType() != null && ex.getRequiredType().equals(UUID.class)) {
            String message = "Полученный id не является UUID";
            log.error(message);
            errorResponse.put("message", message);
            errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        } else {
            log.error(ex.getMessage());
            errorResponse.put("message", ex.getMessage());
            errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        }
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
