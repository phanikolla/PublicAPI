package com.phani.personal.publicapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class StockService {

    @Autowired
    private AlphaVantageConfig config;

    private final RestTemplate restTemplate = new RestTemplate();

    private final ObjectMapper objectMapper = new ObjectMapper();

    public StockDataDto getStockPrice(String symbol) {
        String url = UriComponentsBuilder.fromHttpUrl(config.getBaseUrl())
                .queryParam("function", "TIME_SERIES_INTRADAY")
                .queryParam("symbol", symbol)
                .queryParam("interval", "1min")
                .queryParam("apikey", config.getApiKey())
                .toUriString();

        String response = restTemplate.getForObject(url, String.class);
        return parseStockPrice(symbol, response);
    }

    private StockDataDto parseStockPrice(String symbol, String response) {
        try {
            JsonNode root = objectMapper.readTree(response);
            JsonNode timeSeries = root.path("Time Series (1min)");
            String latestTimestamp = timeSeries.fieldNames().next();
            JsonNode latestData = timeSeries.path(latestTimestamp);

            String price = latestData.path("1. open").asText();
            return new StockDataDto(symbol, price, latestTimestamp);

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse response: " + e.getMessage());
        }
    }
}
