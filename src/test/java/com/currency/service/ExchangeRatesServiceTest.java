package com.currency.service;

import com.currency.configuration.ExchangeRatesResource;
import com.currency.model.RatesResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ExchangeRatesServiceTest {

    @MockBean
    ExchangeRatesResource exchangeRatesResource;
    @Autowired
    ExchangeRatesService exchangeRatesService;

    @Test
    void getActualRates() {
        Mockito.when(exchangeRatesResource.actualRates(Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(new RatesResponse());
        assertNotNull(exchangeRatesService.getActualRates());
    }

    @Test
    void getHistoricalRates() {
        Mockito.when(exchangeRatesResource.historicalRates(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(new RatesResponse());
        assertNotNull(exchangeRatesService.getHistoricalRates("2022-05-01"));
    }
}