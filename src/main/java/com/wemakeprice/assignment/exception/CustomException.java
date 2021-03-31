package com.wemakeprice.assignment.exception;

public class CustomException extends RuntimeException{

    String customMessage;

    public CustomException(String msg){
        this.customMessage = msg;
    }

    public String getCustomMessage(){
        return customMessage;
    }
}
