package com.currency.checker;

import com.currency.model.RatesResponse;
import com.currency.service.DataUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class DataUtilsTest {
    @Test
    void test() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        RatesResponse response = objectMapper.readValue(new File("src/main/resources/latest.json"), RatesResponse.class);

        DataUtils dataUtils = new DataUtils();
        String str = dataUtils.dateToString(dataUtils.getPreviousDate(dataUtils.getDateByTimestamp(response.getTimestamp())));
        System.out.println(dataUtils.dateToString(dataUtils.getPreviousDate(dataUtils.getDateByTimestamp(response.getTimestamp()))));
        System.out.println();
    }
}
