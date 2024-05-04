package aibles.userprofilemanager_1.exception;

import aibles.userprofilemanager_1.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;




import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex)
    {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception ex)
    {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(PostIdNotFoundException.class)
    public ResponseEntity<Object> handlePostIdNotFoundException(PostIdNotFoundException ex) {
        // Giả sử bạn cũng muốn trả về HTTP status NOT_FOUND cho loại ngoại lệ này
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}