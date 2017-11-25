package com.dicka.stock.stockservices.restresource;

import com.dicka.stock.stockservices.model.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.stock.StockQuote;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/rest/stock")
public class RestStock {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping(value = "/{username}")
    public List<Quote> getStock(@PathVariable final String username){

       ResponseEntity<List<String>> quotesResponse = restTemplate
               .exchange("http://db-services/rest/db/"+username,
                       HttpMethod.GET,
                       null,
                       new ParameterizedTypeReference<List<String>>(){});

       List<String> quotes =quotesResponse.getBody();
       return quotes
               .stream()
               .map(quote-> {
                   Stock stock = getStockPrice(quote);
                  return new Quote(quote, stock.getQuote().getPrice());
               })
               .collect(Collectors.toList());
    }

    private Stock getStockPrice(String quote){
        try{
            return YahooFinance.get(quote);
        }catch (IOException e){
            e.printStackTrace();
            return new Stock(quote);
        }
    }
}
