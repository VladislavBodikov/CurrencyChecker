package com.currency.configuration;

import com.currency.model.GifResponse;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
@FeignClient(name = "giphyClient", url = "${gif.url}")
public interface GifResource {
    //api.giphy.com/v1/gifs/search?api_key=JclTvU5vN0Gkwb94qP6P0USO8SVmhfEV&q=rich
        //@RequestLine("GET /random?api_key={appId}&tag={searchWord}")
        @GetMapping("/random")
        ResponseEntity<Map> gifBySearchWord(@RequestParam("api_key") String appId,
                                            @RequestParam("tag") String searchWord);
}
