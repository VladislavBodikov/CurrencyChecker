package com.currency.service;

import com.currency.configuration.GifResource;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GifServiceTest {
    @MockBean
    private GifResource gifResource;
    @MockBean
    private DataUtils dataUtils;
    @Autowired
    private GifService gifService;
    @Value("${gif.search.word.rates.up}")
    private String richWord;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void putGifToResponse() throws IOException {
        MockHttpServletResponse response = new MockHttpServletResponse();
        String gifUrl = "https://i.giphy.com/media/sbguPwYImRDSC5lhFy/giphy.gif";
        ResponseEntity<Map> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        responseEntity = prepareResponseEntity(responseEntity);

        Mockito.when(gifResource.gifBySearchWord(Mockito.any(), Mockito.any())).thenReturn(responseEntity);
        Mockito.when(dataUtils.extractOriginalGifUrl(Mockito.any())).thenReturn(gifUrl);

        gifService.putGifToResponse(richWord, response);
        assertEquals(response.getContentType(), MediaType.IMAGE_JPEG_VALUE);
    }

    private ResponseEntity<Map> prepareResponseEntity(ResponseEntity<Map> responseEntity) throws IOException {
        ResponseEntity<Map<String, LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>>>> entity = null;
        LinkedHashMap<String, String> mp4 = new LinkedHashMap<>() {{
            put("mp4", "https://i.giphy.com/media/sbguPwYImRDSC5lhFy/giphy.gif");
        }};
        LinkedHashMap<String, LinkedHashMap> looping = new LinkedHashMap<>() {{
            put("looping", mp4);
        }};
        LinkedHashMap<String, LinkedHashMap> images = new LinkedHashMap<>() {{
            put("images", looping);
        }};
        Map<String, LinkedHashMap> data = new LinkedHashMap<>() {{
            put("data", images);
        }};
        return new ResponseEntity<>(data, HttpStatus.OK);
    }


}