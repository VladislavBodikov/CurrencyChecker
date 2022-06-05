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
import java.io.InputStreamReader;
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
        URL url = new URL("https://media4.giphy.com/media/sbguPwYImRDSC5lhFy/giphy-hd.mp4?cid=655ea200e853d046d43f7d5ab8bb12ed370e9a3caa603e4a&rid=giphy-hd.mp4&ct=g");
        InputStream in = url.openStream();
        File file = new File("random-gif.mp4");
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
    @Test
    void asdaf() throws IOException {
        GifResponse response = objectMapper.readValue(new File("src/main/resources/random-gif.json"), GifResponse.class);
        System.out.println();
    }

    @Test
    void parseMetaContent() throws IOException {
        URL url = new URL("https://i.giphy.com/media/sbguPwYImRDSC5lhFy/giphy.gif");
        InputStream is = url.openStream();
        InputStreamReader isr = new InputStreamReader(is);
        StringBuilder sb = new StringBuilder();
        int c = isr.read();
        while (c != -1){
            sb.append((char) (c = isr.read()));
        }
        int beginIndexGifUrl = sb.toString().indexOf("meta property=\"og:url\"");
        int endIndexGifUrl = sb.toString().indexOf("<meta property=\"og:title");
        System.out.println();
    }
}
