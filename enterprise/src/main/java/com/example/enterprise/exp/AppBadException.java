package com.example.enterprise.exp;

public class AppBadException extends RuntimeException{
    public AppBadException (String massage){
        super(massage);
    }
}
