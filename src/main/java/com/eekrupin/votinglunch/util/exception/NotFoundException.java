package com.eekrupin.votinglunch.util.exception;

import org.springframework.http.HttpStatus;

//import org.springframework.web.bind.annotation.ResponseStatus;
//@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "No data found")  // 422
public class NotFoundException extends ApplicationException {
    private static final String NOT_FOUND_EXCEPTION = "exception.common.notFound";

    //  http://stackoverflow.com/a/22358422/548473
    public NotFoundException(String arg) {
        super(ErrorType.DATA_NOT_FOUND, NOT_FOUND_EXCEPTION, HttpStatus.UNPROCESSABLE_ENTITY, arg);
    }
}