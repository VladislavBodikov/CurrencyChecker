package com.currency.controller;

import com.currency.checker.DataUtils;
import com.currency.configuration.ExchangeRatesResource;
import com.currency.configuration.GifResource;
import com.currency.model.GifResponse;
import com.currency.model.RatesResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/feign")
public class FeignController {
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


    @Autowired
    private ExchangeRatesResource exchangeRatesResource;
    @Autowired
    private GifResource gifResource;

//    @GetMapping("/{currencyTo}")
//    public String course(@PathVariable("currencyTo") String currencyToCompareWithBase){
//        RatesResponse actualRates = latestRates();
//        String previousDay = getPreviousDay(actualRates);
//        RatesResponse previousRates = exchangeRatesResource.historicalRates(historicalUrl,previousDay, ratesAppId, baseCurrency);
//
//        double actualExchangeRate = actualRates.getRates().get(currencyToCompareWithBase);
//        double previousExchangeRate = previousRates.getRates().get(currencyToCompareWithBase);
//
//        if (actualExchangeRate > previousExchangeRate){
//            // callback RICH GIF
//        } else {
//            // callback POOR GIF
//        }
//        return "";
//    }
    private RatesResponse latestRates(){
        return exchangeRatesResource.actualRates(latestRatesUrl, ratesAppId, baseCurrency);
    }
    private String getPreviousDay(RatesResponse todayRates){
        DataUtils dataUtils = new DataUtils();
        LocalDateTime date = dataUtils.getDateByTimestamp(todayRates.getTimestamp());
        LocalDateTime previousDate = dataUtils.getPreviousDate(date);
        return dataUtils.dateToString(previousDate);
    }
    @GetMapping("/gif/")
    private ResponseEntity<Map> getGifResource(HttpServletResponse httpServletResponse) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        GifResponse json = objectMapper.readValue(new File("src/main/resources/gif.json"), GifResponse.class);
//
//        int gifNumber = selectRandomGifNumber(json);
//        LinkedHashMap<String,String> randomGifData = (LinkedHashMap<String, String>) json.getData()[gifNumber];
//        StringBuilder join = new StringBuilder();
//        for (String key : randomGifData.keySet()){
//            join.append(key).append("\t-->\t").append(randomGifData.get(key)).append("\n");
//        }
//        httpServletResponse.sendRedirect("https://media0.giphy.com/media/LdOyjZ7io5Msw/giphy.gif?cid=655ea200kn3xpma6yhqs5aif6wss6sjxso3508r0oyyroda7&rid=giphy.gif&ct=g");
        ResponseEntity<Map> response = gifResource.gifBySearchWord(gifAppId,gifSearchRatesUp);
        System.out.println();
        return response;
    }

    @GetMapping("/image-manual-response")
    public void getImageAsByteArray(HttpServletResponse response) throws IOException {
//        URI uri = new URI("https://i.giphy.com/media/LdOyjZ7io5Msw/giphy.gif");
        URL url = new URL("https://i.giphy.com/media/LdOyjZ7io5Msw/giphy.gif");

//        InputStream in = new FileInputStream(new File(new URI()));
        InputStream in = url.openStream();
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(in, response.getOutputStream());
    }

//    private void asdf(){
//        URL address = new URL(webPathToImage);
//        InputStream in = address.openStream();
//        File file = new File(destination);
//        if (!file.exists()){
//            Files.copy(in, file.toPath());
//        }
//        in.close();
//
//    }
//    private int selectRandomGifNumber(GifResponse gifResponse){
//        int gifCount = gifResponse.getData().length;
//        return (int)Math.round(Math.random() * (gifCount - 1));
//    }
}
