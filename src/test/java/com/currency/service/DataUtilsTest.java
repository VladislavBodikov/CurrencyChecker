package com.currency.service;

import com.currency.model.RatesResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DataUtilsTest {

    @Autowired
    private DataUtils dataUtils;

    long timestamp = 1654451986820L / 1000; // 05.06.2022

    @Test
    void getDateByTimestamp(){

        LocalDateTime actual = dataUtils.getDateByTimestamp(timestamp);
        LocalDateTime expect = LocalDateTime.ofEpochSecond(timestamp,0, ZoneOffset.ofHours(0));

        assertEquals(actual,expect);

    }
    @Test
    void dateToString(){
        LocalDateTime date = LocalDateTime.of(2022,6, 5,1,1);

        String actual = dataUtils.dateToString(date);
        String expected = "2022-06-05";

        assertEquals(expected,actual);
    }

    @Test
    void getPreviousDate(){
        LocalDateTime date = LocalDateTime.of(2022,6, 5,1,1);

        LocalDateTime actual = dataUtils.getPreviousDate(date);
        LocalDateTime expected = date.minusDays(1);

        assertEquals(expected,actual);
    }

    @Test
    void extractOriginalGifUrl(){

    }

    @Test
    void getDateAtPreviousDay(){
        RatesResponse ratesResponse = new RatesResponse();
        ratesResponse.setTimestamp(timestamp);

        String actual = dataUtils.getDateAtPreviousDay(ratesResponse);
        String expected = "2022-06-04";

        assertEquals(expected, actual);
    }


}