package com.gleisonoliveira.personapi.Exceptions;

public class RequiredObjectIsNullException extends BaseException {
    private static final String exceptionTemplate = "The object '%s' can`t be null";
    public RequiredObjectIsNullException() {
    }

    public RequiredObjectIsNullException(String resource) {
        super(String.format(exceptionTemplate, resource));
    }
}
