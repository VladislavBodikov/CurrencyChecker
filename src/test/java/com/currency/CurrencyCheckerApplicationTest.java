package com.currency;

import com.currency.service.ExchangeRatesService;
import com.currency.service.GifService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CurrencyCheckerApplicationTest {

    @Autowired
    private GifService gifService;
    @Autowired
    private ExchangeRatesService exchangeRatesService;

    @Test
    void loadContext(){
        assertAll(  ()->assertNotNull(gifService),
                    ()->assertNotNull(exchangeRatesService)
        );
    }

}