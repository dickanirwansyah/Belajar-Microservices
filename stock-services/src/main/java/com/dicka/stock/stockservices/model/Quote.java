package com.dicka.stock.stockservices.model;

import java.math.BigDecimal;

public class Quote {

    private String quote;
    BigDecimal price;

    public Quote(String quote, BigDecimal price){
        this.quote = quote;
        this.price = price;
    }

    public String getQuote(){
        return quote;
    }

    public void setQuote(String Quote){
        this.quote = quote;
    }

    public BigDecimal getPrice(){
        return price;
    }

    public void setPrice(BigDecimal price){
        this.price = price;
    }
}
