package com.eekrupin.votinglunch.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    public static String toString(LocalDate date) {
        return date == null ? "" : date.format(DATE_FORMATTER);
    }
}
