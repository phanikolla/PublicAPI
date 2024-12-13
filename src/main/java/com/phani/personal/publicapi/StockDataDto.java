package com.phani.personal.publicapi;

public class StockDataDto {
    private String symbol;
    private String price;
    private String timestamp;

    public StockDataDto(String symbol, String price, String timestamp) {
        this.symbol = symbol;
        this.price = price;
        this.timestamp = timestamp;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getPrice() {
        return price;
    }

    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "StockDataDto{" +
                "symbol='" + symbol + '\'' +
                ", price='" + price + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
