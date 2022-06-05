package com.currency.controller;

import com.currency.service.DataUtils;
import com.currency.model.RatesResponse;
import com.currency.service.ExchangeRatesService;
import com.currency.service.GifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;


@RestController
@RequestMapping("/currency")
public class MainController {

    @Value("${gif.search.word.rates.up}")
    private String gifSearchRatesUp;
    @Value("${gif.search.word.rates.down}")
    private String gifSearchRatesDown;

    @Autowired
    private DataUtils dataUtils;
    @Autowired
    private ExchangeRatesService exchangeRatesService;
    @Autowired
    private GifService gifService;

    @GetMapping("/{currencyTo}")
    public void course(@PathVariable("currencyTo") String currencyToCompareWithBase, HttpServletResponse response){
        RatesResponse actualRates = exchangeRatesService.getActualRates();
        RatesResponse previousRates = exchangeRatesService.getHistoricalRates(dataUtils.getDateAtPreviousDay(actualRates));

        double actualExchangeRate = actualRates.getRates().get(currencyToCompareWithBase);
        double previousExchangeRate = previousRates.getRates().get(currencyToCompareWithBase);

        try {
            if (actualExchangeRate > previousExchangeRate){
                gifService.putGifToResponse(gifSearchRatesUp,response);
            } else {
                gifService.putGifToResponse(gifSearchRatesDown,response);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
