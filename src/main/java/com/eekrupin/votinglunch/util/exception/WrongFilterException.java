package com.eekrupin.votinglunch.util.exception;

import org.springframework.http.HttpStatus;

public class WrongFilterException extends ApplicationException {
    private static final String VALIDATION_ERROR = "exception.common.invalidFilter";

    public WrongFilterException(String arg) {
        super(ErrorType.VALIDATION_ERROR, VALIDATION_ERROR, HttpStatus.UNPROCESSABLE_ENTITY, arg);
    }
}