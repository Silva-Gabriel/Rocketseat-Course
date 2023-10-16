package com.avanade.todolist.exceptions.exception;

public class SizeLimitExceededException extends RuntimeException{
    public SizeLimitExceededException(String message){
        super(message);
    }
}
