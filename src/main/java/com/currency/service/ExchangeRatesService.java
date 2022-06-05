package com.currency.service;

import com.currency.configuration.ExchangeRatesResource;
import com.currency.model.RatesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ExchangeRatesService {
    // rates config
    @Value("${rates.latest}")
    private String latestRatesUrl;
    @Value("${rates.historical}")
    private String historicalUrl;
    @Value("${currency.base}")
    private String baseCurrency;
    @Value("${rates.app.id}")
    private String ratesAppId;

    @Autowired
    private ExchangeRatesResource exchangeRatesResource;

    public RatesResponse getActualRates(){
        return exchangeRatesResource.actualRates(latestRatesUrl, ratesAppId, baseCurrency);
    }

    public RatesResponse getHistoricalRates(String date){
        return exchangeRatesResource.historicalRates(historicalUrl, date, ratesAppId, baseCurrency);
    }
}
