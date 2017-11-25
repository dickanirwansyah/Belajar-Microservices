package com.dicka.stock.dbservices.restresource;

import com.dicka.stock.dbservices.model.Quote;
import com.dicka.stock.dbservices.model.Quotes;
import com.dicka.stock.dbservices.repository.RepositoryQuote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/rest/db")
public class RestDBresources {

    @Autowired
    private RepositoryQuote repositoryQuote;

    //get data
    @GetMapping(value = "/{username}")
    public List<String> getQuotes(@PathVariable final String username){

        return getQuotesByUserName(username);
    }

    //post delete
    @PostMapping("/delete/{username}")
    public List<String> delete(@PathVariable final String username){

        List<Quote> quotes = repositoryQuote.findByusername(username);

        repositoryQuote.delete(quotes);
        return getQuotesByUserName(username);
    }

    //post add
    @PostMapping(value = "/add")
    public List<String> add(@RequestBody final Quotes quotes){

        quotes.getQuote().stream()
                .map(quotess -> new Quote(quotes.getUsername(), quotess))
                .forEach(quotess -> {
                    repositoryQuote.save(quotess);
                });

        return getQuotesByUserName(quotes.getUsername());
    }

    //methode list usernmae
    private List<String> getQuotesByUserName(@PathVariable String username){
        return repositoryQuote.findByusername(username)
                .stream()
                .map(Quote::getQuote)
                .collect(Collectors.toList());
    }
}
