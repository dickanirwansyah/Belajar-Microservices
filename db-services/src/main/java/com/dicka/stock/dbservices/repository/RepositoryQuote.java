package com.dicka.stock.dbservices.repository;

import com.dicka.stock.dbservices.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface RepositoryQuote extends JpaRepository<Quote, Integer>{

    List<Quote> findByusername(String username);
}
