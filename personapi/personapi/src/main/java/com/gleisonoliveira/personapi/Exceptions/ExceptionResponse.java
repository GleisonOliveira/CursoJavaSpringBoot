package com.gleisonoliveira.personapi.Exceptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExceptionResponse implements IExceptionResponse {
    private Date timestamp;
    private String message;
    private String details;
    public List<?> errors;

    public ExceptionResponse(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        errors = new ArrayList<String>();
    }

    public ExceptionResponse(Date timestamp, String message, String details, List<?> errors) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.errors = errors;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
  
    public List<?> getErrors() {
        return errors;
    }
}
