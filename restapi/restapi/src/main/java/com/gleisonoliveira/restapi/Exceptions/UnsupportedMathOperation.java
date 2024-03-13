package com.gleisonoliveira.restapi.Exceptions;

import java.util.List;

public class UnsupportedMathOperation extends BaseException {
    public UnsupportedMathOperation() {
    }

    public UnsupportedMathOperation(String ex) {
        super(ex);
    }

    public UnsupportedMathOperation(String ex, List<?> errors) {
        super(ex);
        this.errors = errors;
    }
}
