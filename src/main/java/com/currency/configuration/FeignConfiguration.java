package com.currency.configuration;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.gson.GsonBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.json.AbstractJackson2Encoder;

@Configuration
public class FeignConfiguration {

    @Value("${rates.url}")
    private String urlRates;
    @Value("${gif.url}")
    private String urlGif;

    @Bean
    public ExchangeRatesResource exchangeRatesResource(){
        return Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .target(ExchangeRatesResource.class,urlRates);
    }

//    @Bean
//    public GifResource gifResource(){
//        return Feign.builder()
//                .encoder(new JacksonEncoder())
//                .decoder(new JacksonDecoder())
//                .target(GifResource.class, urlGif);
//    }


}
