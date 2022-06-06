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
@RequestMapping("/currency/compare-base-with")
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
    public String compareExchangeRates(@PathVariable("currencyTo") String currencyToCompareWithBase, HttpServletResponse response){
        RatesResponse actualRates = exchangeRatesService.getActualRates();
        RatesResponse previousRates = exchangeRatesService.getHistoricalRates(dataUtils.getDateAtPreviousDay(actualRates));

        boolean isRatesUp = exchangeRatesService.isRatesUp(previousRates,actualRates,currencyToCompareWithBase);

        try {
            if (isRatesUp){
                gifService.putGifToResponse(gifSearchRatesUp,response);
                return "RATES UP!";
            } else {
                gifService.putGifToResponse(gifSearchRatesDown,response);
                return "RATES DOWN OR NOT CHANGED FOR LAST 24 HOURS";
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return "SOMETHING WRONG!";
    }
}
