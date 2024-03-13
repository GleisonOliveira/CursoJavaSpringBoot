package com.gleisonoliveira.restapi.Exceptions;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface IExceptionResponse extends Serializable {
    public static final Date timestamp = new Date();
    public static final String message = "";
    public static final String details = "";
    public static final List<?> errors = null;

    /**
     * Get the timestamp from request
     * 
     * @return
     */
    public Date getTimestamp();

    /**
     * Get the error message from request
     * 
     * @return
     */
    public String getMessage();

    /**
     * Get the error details from request
     * 
     * @return
     */
    public String getDetails();

    /**
     * Get a list of errors
     * 
     * @return
     */
    public List<?> getErrors();
}
