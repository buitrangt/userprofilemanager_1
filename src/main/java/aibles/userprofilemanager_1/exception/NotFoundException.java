package aibles.userprofilemanager_1.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}