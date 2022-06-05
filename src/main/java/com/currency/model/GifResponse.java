package com.currency.model;

import lombok.Data;

import java.util.Map;

@Data
public class GifResponse {

    private Map<String,Map> data;
//    private Map<String,String> pagination;
    private Map<String,String> meta;
}
