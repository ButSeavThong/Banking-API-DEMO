package kh.coding.fullstackjpa.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
@RestControllerAdvice
public class ServiceException{

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handleServiceException(ResponseStatusException ex){
        ErrorResponse<String> errorResponse =  ErrorResponse.<String>builder()
                .message(" Error Logic In Service  ")
                .status(ex.getStatusCode().value())
                .timestamp(LocalDateTime.now())
                .detail(ex.getReason())
                .build();
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponse);
    }
}

