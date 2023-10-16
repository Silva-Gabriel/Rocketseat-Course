package com.avanade.todolist.exceptions.enums;

import ch.qos.logback.classic.spi.ILoggingEvent;
import lombok.Getter;

@Getter
public enum ErrorMessageEnum {
    USERNAME_ALREADY_EXISTS("username-already-exists"),
    USERNAME_NOT_FOUND("username-not-found"),
    START_DATE_IS_AFTER_CURRENT_DATE("start-date-is-after-current-date"),
    END_DATE_IS_BEFORE_START_DATE("end-date-is-before-start-date"),
    INCORRECT_USERNAME_OR_PASSWORD("incorrect-username-or-password"),
    ENTITY_NOT_FOUND("entity-not-found"),
    USER_NOT_AUTHORIZED("user-not-authorized"),
    SIZE_LIMIT_EXCEEDED("size-limit-exceeded");
    private final String message;
    ErrorMessageEnum(String message){
        this.message = message;
    }
}
