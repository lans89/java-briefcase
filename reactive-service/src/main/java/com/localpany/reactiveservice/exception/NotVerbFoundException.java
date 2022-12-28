package com.localpany.reactiveservice.exception;

public class NotVerbFoundException extends Exception{

    private String finalMessage= "";

    public NotVerbFoundException(Long id){
        super();
        finalMessage = String.format("No se encontro verbo con Id: %s", id.toString());
    }

    public NotVerbFoundException(Long id, Throwable throwable){
        super();
        finalMessage = String.format("No se encontro verbo con Id: %s razon: %s", id.toString(), throwable.getMessage());
    }

    @Override
    public  String getMessage(){
        return finalMessage;
    }
}
