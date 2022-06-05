package com.currency.service;

import com.currency.configuration.GifResource;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class GifService {

    @Value("${gif.app.id}")
    private String gifAppId;

    @Autowired
    private DataUtils dataUtils;
    @Autowired
    private GifResource gifResource;

    public void putGifToResponse(String searchWord, HttpServletResponse response) throws IOException {
        ResponseEntity<Map> resourceResponse = gifResource.gifBySearchWord(gifAppId,searchWord);

        String gifUrl = (String) ((LinkedHashMap)((LinkedHashMap)(((LinkedHashMap)resourceResponse.getBody().get("data")).get("images"))).get("looping")).get("mp4");
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
