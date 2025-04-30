package com.javatecharc.learning.advoice;

import com.javatecharc.learning.exception.EmployeeNotFound;
import com.javatecharc.learning.exception.SpringLearningException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SpringLearningExceptionHandler {
    // This class is used to handle exceptions globally in the application
    // You can define methods here to handle specific exceptions and return custom responses
    // For example, you can use @ExceptionHandler annotation to handle specific exceptions
    // and return a custom response
    // Example:

/*    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }*/

    @ExceptionHandler(SpringLearningException.class)
    public ResponseEntity<String> handleLearningException(SpringLearningException e) {
        System.out.println("SpringLearningException: " + e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmployeeNotFound.class)
    public ResponseEntity<String> handleEmployeeNotFound(EmployeeNotFound e) {
        System.out.println("EmployeeNotFound : " + e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
