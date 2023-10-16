package com.avanade.todolist.exceptions.exception;

public class EndDateIsBeforeStartDate extends RuntimeException {
    public EndDateIsBeforeStartDate(String message){
        super(message);
    }
}
