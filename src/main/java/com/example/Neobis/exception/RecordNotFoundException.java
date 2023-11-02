package com.example.Neobis.exception;

public class RecordNotFoundException extends  RuntimeException{
    public RecordNotFoundException(String message){
        super(message);
    }
}
