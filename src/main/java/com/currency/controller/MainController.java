package com.currency.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/currency/USD/to/")
public class MainController {

    @Value("${compareCurrencyWith}")
    private String currency;

    @GetMapping("/{currencyTo}")
    public String getResponse(@PathVariable(value = "currencyTo") String currencyTo) {
        return currencyTo;
    }

    @GetMapping()
    public String checkServer(){

        return currency;
    }
}
