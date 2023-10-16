package com.avanade.todolist.exceptions.exception;

public class UsernameAlreadyExists extends RuntimeException{
    public UsernameAlreadyExists(String message){
        super(message);
    }
}
