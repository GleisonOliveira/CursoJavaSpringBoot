package com.gleisonoliveira.personapi.Exceptions;

public class ResourceNotFoundException extends BaseException {
    private static final String exceptionTemplate = "The resource '%s' was not founded";
    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String resource) {
        super(String.format(exceptionTemplate, resource));
    }
}
