package com.currency.configuration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "giphyClient", url = "${gif.url}")
public interface GifResource {

    @GetMapping("/random")
    ResponseEntity<Map> gifBySearchWord(@RequestParam("api_key") String appId,
                                        @RequestParam("tag") String searchWord);
}
