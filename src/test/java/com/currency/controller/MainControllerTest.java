package com.currency.controller;

import com.currency.configuration.GifResource;
import com.currency.model.RatesResponse;
import com.currency.service.ExchangeRatesService;
import com.currency.service.GifService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;

import static com.currency.TestUtils.getPreparedRates;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class MainControllerTest {
    @MockBean
    private GifService gifService;
    @MockBean
    private ExchangeRatesService exchangeRatesService;
    @Autowired
    private MainController controller;
    @MockBean
    private GifResource gifResource;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;


    @Test
    void ratesDown() throws Exception {
//        RestTemplate restTemplate = restTemplateBuilder.build();
//
//        MockHttpServletResponse response = new MockHttpServletResponse();
        Mockito.when(exchangeRatesService.getActualRates()).thenReturn(getPreparedRates());
        Mockito.when(exchangeRatesService.getHistoricalRates(Mockito.any())).thenReturn(getPreparedRates());
        Mockito.when(gifResource.gifBySearchWord(Mockito.any(),Mockito.any())).thenReturn(new ResponseEntity<Map>(HttpStatus.OK));

        String expected = "RATES DOWN OR NOT CHANGED FOR LAST 24 HOURS";

        mockMvc.perform(MockMvcRequestBuilders.get("/currency/compare-base-with/RUB"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(expected)));
    }

    @Test
    void ratesUp() throws Exception {
        RatesResponse ratesBigger = getPreparedRates();
        RatesResponse ratesSmaller = getPreparedRates();
        ratesSmaller.getRates().put("RUB",
                ratesBigger.getRates().get("RUB") / 2.0);

        Mockito.when(exchangeRatesService.getActualRates()).thenReturn(ratesBigger);
        Mockito.when(exchangeRatesService.getHistoricalRates(Mockito.any())).thenReturn(ratesSmaller);
        Mockito.when(exchangeRatesService.isRatesUp(Mockito.any(), Mockito.any(), Mockito.any())).thenCallRealMethod();
        Mockito.when(gifResource.gifBySearchWord(Mockito.any(),Mockito.any())).thenReturn(new ResponseEntity<Map>(HttpStatus.OK));

        String expected = "RATES UP";

        mockMvc.perform(MockMvcRequestBuilders.get("/currency/compare-base-with/RUB"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(expected)));
    }
}