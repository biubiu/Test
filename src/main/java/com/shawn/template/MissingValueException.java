package com.shawn.template;

public class MissingValueException extends RuntimeException{

    public MissingValueException(String message){
        super(message);
    }
}
