package com.currency.service;

import com.currency.model.RatesResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class DataUtils {

    private final int DAYS_BEFORE_ACTUAL_CHECK = 1; // 1 - yesterday rates


    public LocalDateTime getDateByTimestamp(long timestamp) {
        return LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.ofHours(0));
    }

    public LocalDateTime getPreviousDate(LocalDateTime date) {
        return date.minusDays(DAYS_BEFORE_ACTUAL_CHECK);
    }

    public String dateToString(LocalDateTime date) {
        String day = addZeroFirstIfOneDigit(String.valueOf(date.getDayOfMonth()));
        String month = addZeroFirstIfOneDigit(String.valueOf(date.getMonthValue()));
        String year = String.valueOf(date.getYear());
        return String.format("%s-%s-%s", year, month, day);
    }

    private String addZeroFirstIfOneDigit(String digit) {
        return (digit.length() == 1) ? "0" + digit : digit;
    }

    // parse original gif_url to show without GIPHY watermarks
    public String extractOriginalGifUrl(String gifUrl) throws IOException {
        final String findBeginIndex = "<meta property=\"og:url\" content=\"";
        final String findEndIndex = "<meta property=\"og:title";

        URL url = new URL(gifUrl);
        InputStream is = url.openStream();
        InputStreamReader isr = new InputStreamReader(is);
        StringBuilder sb = new StringBuilder();
        int c = isr.read();
        while (c != -1) {
            sb.append((char) (c = isr.read()));
        }
        int beginIndexGifUrl = sb.toString().indexOf(findBeginIndex) + findBeginIndex.length();
        int endIndexGifUrl = sb.toString().indexOf(findEndIndex);
        String originalGif = sb.substring(beginIndexGifUrl, endIndexGifUrl).trim();
        originalGif = originalGif.substring(0, originalGif.length() - 2);

        return originalGif;
    }

    public String getDateAtPreviousDay(RatesResponse todayRates) {
        DataUtils dataUtils = new DataUtils();
        LocalDateTime date = dataUtils.getDateByTimestamp(todayRates.getTimestamp());
        LocalDateTime previousDate = dataUtils.getPreviousDate(date);
        return dataUtils.dateToString(previousDate);
    }
}
