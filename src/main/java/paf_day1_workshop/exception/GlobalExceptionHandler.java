package paf_day1_workshop.exception;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class GlobalExceptionHandler {
    public static final Logger LOGGER = LoggerFactory.getLogger(
            GlobalExceptionHandler.class);

    public ResponseEntity<ErrorMessage> handleResourceNotFoundException(
        ResourceNotFoundException ex, WebRequest request) {

        ErrorMessage msg = new ErrorMessage(HttpStatus.NOT_FOUND.value(), 
                new Date(), 
                ex.getMessage(), 
                request.getDescription(false));
            
        return new ResponseEntity<ErrorMessage>(msg, HttpStatus.NOT_FOUND);
    }
}
