package com.eekrupin.votinglunch.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ForbiddenException extends ApplicationException {
    private static final String FORBIDDEN = "exception.common.forbidden";

    public ForbiddenException(String arg) {
        super(ErrorType.FORBIDDEN, FORBIDDEN, HttpStatus.FORBIDDEN, arg);
    }
}