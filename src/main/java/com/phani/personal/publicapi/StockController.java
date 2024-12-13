package com.phani.personal.publicapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/stock")
    public StockDataDto getStockPrice(@RequestParam String symbol) {
        return stockService.getStockPrice(symbol);
    }
}
