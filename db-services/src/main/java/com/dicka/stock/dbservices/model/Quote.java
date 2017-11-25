package com.dicka.stock.dbservices.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "quotes",
        catalog = "microservice")
public class Quote implements Serializable{

    @Id
    @Column(name = "idquote")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idquote;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "quote", nullable = false)
    private String quote;


    public Quote(){

    }

    public Quote(String username, String quote){
        this.username = username;
        this.quote = quote;
    }

    public int getIdquote(){
        return idquote;
    }

    public void setIdquote(int idquote){
        this.idquote = idquote;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getQuote(){
        return quote;
    }

    public void setQuote(String quote){
        this.quote = quote;
    }
}
