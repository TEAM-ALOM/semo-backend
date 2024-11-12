package com.semo.semo.global.error;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiErrorResponse {
    private String message;
    public ApiErrorResponse(String message) {
        this.message = message;
    }

}