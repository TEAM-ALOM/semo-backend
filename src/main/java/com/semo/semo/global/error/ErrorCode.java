package com.semo.semo.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found"),
    PARAMETER_NOT_FOUND(HttpStatus.BAD_REQUEST, "Parameter not found"),
    USER_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "User already exists"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");

    private final HttpStatus status;
    private final String message;
}
