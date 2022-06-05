package com.currency.controller;

import com.currency.checker.DataUtils;
import com.currency.configuration.ExchangeRatesResource;
import com.currency.configuration.GifResource;
import com.currency.model.RatesResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;


@RestController
@RequestMapping("/currency")
public class MainController {
    // rates config
    @Value("${rates.latest}")
    private String latestRatesUrl;
    @Value("${rates.historical}")
    private String historicalUrl;
    @Value("${currency.base}")
    private String baseCurrency;
    @Value("${rates.app.id}")
    private String ratesAppId;
    // gif config
    @Value("${gif.app.id}")
    private String gifAppId;
    @Value("${gif.search.word.rates.up}")
    private String gifSearchRatesUp;
    @Value("${gif.search.word.rates.down}")
    private String gifSearchRatesDown;

    private DataUtils dataUtils = new DataUtils();

    @Autowired
    private ExchangeRatesResource exchangeRatesResource;
    @Autowired
    private GifResource gifResource;

    @GetMapping("/{currencyTo}")
    public void course(@PathVariable("currencyTo") String currencyToCompareWithBase, HttpServletResponse response){
        RatesResponse actualRates = getLatestRates();
        RatesResponse previousRates = getHistoricalRates(dataUtils.getDateAtPreviousDay(actualRates));

        double actualExchangeRate = actualRates.getRates().get(currencyToCompareWithBase);
        double previousExchangeRate = previousRates.getRates().get(currencyToCompareWithBase);

        try {
            if (actualExchangeRate > previousExchangeRate){
                getGifResource(gifSearchRatesUp,response);
            } else {
                getGifResource(gifSearchRatesDown,response);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    private RatesResponse getLatestRates(){
        return exchangeRatesResource.actualRates(latestRatesUrl, ratesAppId, baseCurrency);
    }
    private RatesResponse getHistoricalRates(String date){
        return exchangeRatesResource.historicalRates(historicalUrl,date, ratesAppId, baseCurrency);
    }


    private void getGifResource(String searchWord,HttpServletResponse response) throws IOException {
        ResponseEntity<Map> resourceResponse = gifResource.gifBySearchWord(gifAppId,searchWord);
        String gifUrl = (String) ((LinkedHashMap)((LinkedHashMap)(((LinkedHashMap)resourceResponse.getBody().get("data")).get("images"))).get("original")).get("url");
        String originalGifUrl = dataUtils.extractOriginalGifUrl(gifUrl);

        getImageAsByteArray(originalGifUrl,response);
    }


    private void getImageAsByteArray(String gifUrl,HttpServletResponse response) throws IOException {
        URL url = new URL(gifUrl);
        InputStream in = url.openStream();
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(in, response.getOutputStream());
    }

}
