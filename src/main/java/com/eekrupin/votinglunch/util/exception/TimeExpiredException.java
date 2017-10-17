package com.eekrupin.votinglunch.util.exception;

import org.springframework.http.HttpStatus;

public class TimeExpiredException extends ApplicationException {
    private static final String TIME_EXPIRED = "exception.common.timeExpired";

    public TimeExpiredException(String arg) {
        super(ErrorType.TIME_EXPIRED, TIME_EXPIRED, HttpStatus.CONFLICT, arg);
    }
}