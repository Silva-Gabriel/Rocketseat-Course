package com.avanade.todolist.exceptions.exception;

public class UnauthorizedUser extends RuntimeException{
    public UnauthorizedUser(String message){
        super(message);
    }
}
