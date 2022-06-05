package com.currency;

import com.currency.model.GifResponse;
import com.currency.model.RatesResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;

public class JsonSerializeTest {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @Disabled
    void readJson() throws IOException {

        RatesResponse json = objectMapper.readValue(new File("src/main/resources/latest.json"), RatesResponse.class);
        System.out.println();
    }

    @Test
    void readGif() throws IOException {
        GifResponse json = objectMapper.readValue(new File("src/main/resources/single-data.json"), GifResponse.class);
        System.out.println();
    }

    @Test
    void saveMp4() throws IOException {
//        URL url = new URL("https://i.giphy.com/media/LdOyjZ7io5Msw/giphy.gif");
        URL url = new URL("https://media0.giphy.com/media/LdOyjZ7io5Msw/giphy.gif");
        InputStream in = url.openStream();
        File file = new File("gif.mp4");
        if (!file.exists()){
            Files.copy(in, file.toPath());
        }
        in.close();

    }
    @Test
    void imageTest() throws IOException {
        ImageIO.read(new File("gif.gif"));
    }

    @Test
    void responseEntity() throws IOException {
        ResponseEntity response = objectMapper.readValue(new File("src/main/resources/response.json"), ResponseEntity.class);
        System.out.println();
    }
}
