package com.currency;

import com.currency.model.RatesResponse;

import java.util.HashMap;

public class TestUtils {

    public static RatesResponse getPreparedRates(){
        RatesResponse response = new RatesResponse();
        long timestamp = 1654451986820L / 1000;
        response.setRates(new HashMap<>(){{put("RUB",100.0);}});
        response.setTimestamp(timestamp);
        return response;
    }
}
