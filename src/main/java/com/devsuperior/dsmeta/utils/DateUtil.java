package com.devsuperior.dsmeta.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public final class DateUtil {
    private DateUtil() {
    }

    public static LocalDate getMaxDate(String maxDate) {
        if(!maxDate.equals("")) {
            return LocalDate.parse(maxDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        return LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
    }

    public static LocalDate getMinDate(LocalDate max, String minDate) {
        if(!minDate.equals("")) {
            return LocalDate.parse(minDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        return max.minusYears(1L);
    }
}
