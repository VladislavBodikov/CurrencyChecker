package com.currency.configuration;

import com.currency.model.RatesResponse;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ratesClient", url = "${rates.url}")
public interface ExchangeRatesResource {

        //@RequestLine("GET /{latestRatesUrl}.json?app_id={app_id}&base={baseCurrency}")
        @GetMapping("/{latestUrl}.json")
        RatesResponse actualRates(@PathVariable("latestUrl") String latestUrl,
                                  @RequestParam("app_id") String appId,
                                  @RequestParam("base") String baseCurrency);

//        @RequestLine("GET /{historicalRatesUrl}/{dateOfRates}.json?app_id={app_id}&base={baseCurrency}")
        @GetMapping("/{historicalUrl}/{dateOfRates}.json")
        RatesResponse historicalRates(@PathVariable("historicalUrl") String historicalUrl,
                                      @PathVariable("dateOfRates") String dateOfRates,
                                      @RequestParam("app_id") String appId,
                                      @RequestParam("baseCurrency") String baseCurrency);

}
