package com.avanade.todolist.exceptions.exception;

public class StartDateIsAfterCurrentDate extends RuntimeException{
    public StartDateIsAfterCurrentDate(String message){
        super(message);
    }
}
