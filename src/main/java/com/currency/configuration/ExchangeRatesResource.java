package com.currency.configuration;

import com.currency.model.RatesResponse;
import feign.Param;
import feign.RequestLine;

public interface ExchangeRatesResource {

        @RequestLine("GET /{latestRatesUrl}.json?app_id={app_id}&base={baseCurrency}")
        RatesResponse actualRates(@Param("latestRatesUrl") String latestRatesUrl,
                                  @Param("app_id") String appId,
                                  @Param("baseCurrency") String baseCurrency);

        @RequestLine("GET /{historicalRatesUrl}/{dateOfRates}.json?app_id={app_id}&base={baseCurrency}")
        RatesResponse historicalRates(@Param("historicalRatesUrl") String historicalRatesUrl,
                                      @Param("dateOfRates") String dateOfRates,
                                      @Param("app_id") String appId,
                                      @Param("baseCurrency") String baseCurrency);

}
