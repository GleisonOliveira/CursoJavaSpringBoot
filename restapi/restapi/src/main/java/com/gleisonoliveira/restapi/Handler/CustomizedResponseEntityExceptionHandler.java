package com.gleisonoliveira.restapi.Handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.gleisonoliveira.restapi.Exceptions.ExceptionResponse;
import com.gleisonoliveira.restapi.Exceptions.IExceptionResponse;
import com.gleisonoliveira.restapi.Exceptions.UnsupportedMathOperation;

@RestController
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle all exceptions when is an exception class
     * 
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<IExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handle all exceptions when is an UnsupportedMathOperation class
     * 
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(UnsupportedMathOperation.class)
    public final ResponseEntity<IExceptionResponse> handleAllExceptions(UnsupportedMathOperation ex,
            WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false),
                ex.getErrors());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
