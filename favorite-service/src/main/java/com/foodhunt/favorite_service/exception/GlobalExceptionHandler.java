package com.foodhunt.favorite_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FavoriteAlreadyExistsException.class)
    public ResponseEntity<?>handleFavoriteAlreadyExists(FavoriteAlreadyExistsException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of(
                    "timestamp",LocalDateTime.now(),
                    "message",ex.getMessage()
                ));
    }


@ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<?>handleResourceNotFound(ResourceNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(Map.of(
                    "timestamp", LocalDateTime.now(),
                    "message", ex.getMessage()
            ));
}
}