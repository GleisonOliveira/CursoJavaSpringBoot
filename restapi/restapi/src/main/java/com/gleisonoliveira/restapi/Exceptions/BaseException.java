package com.gleisonoliveira.restapi.Exceptions;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseException extends Exception {
    public List<?> errors = new ArrayList<String>();

    public BaseException() {
    }

    public BaseException(String ex) {
        super(ex);
    }

    public BaseException(String ex, List<?> errors) {
        super(ex);
        this.errors = errors;
    }

    /**
     * Get a list of errors
     * 
     * @return
     */
    public List<?> getErrors() {
        return errors;
    }
}
