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
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"),
    USER_SIGNUP_FAIL(HttpStatus.BAD_REQUEST, "User signup failed"),
    USER_LOGIN_FAIL(HttpStatus.BAD_REQUEST, "User login failed"),
    AUTH_FAIL(HttpStatus.BAD_REQUEST,"Authentication failed");

    private final HttpStatus status;
    private final String message;
}
