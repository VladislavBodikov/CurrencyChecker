package com.currency.checker;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class DataUtils {

    private final int DAYS_BEFORE_ACTUAL_CHECK = 1; // 1 - yesterday rates


    public LocalDateTime getDateByTimestamp(long timestamp){
        return LocalDateTime.ofEpochSecond(timestamp, 0,ZoneOffset.ofHours(0));
    }

    public LocalDateTime getPreviousDate(LocalDateTime date){
        return date.minusDays(DAYS_BEFORE_ACTUAL_CHECK);
    }

    public String dateToString(LocalDateTime date){
        String day = addZeroFirstIfOneDigit(String.valueOf(date.getDayOfMonth()));
        String month = addZeroFirstIfOneDigit(String.valueOf(date.getMonthValue()));
        String year = String.valueOf(date.getYear());
        return String.format("%s-%s-%s",year,month,day);
    }

    private String addZeroFirstIfOneDigit(String digit){
        return (digit.length() == 1) ? "0" + digit : digit;
    }
}
