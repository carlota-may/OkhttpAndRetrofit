package com.zxm.okhttp;

import lombok.Data;

@Data
public class ApiErrorModel {
    private int status;
    private String message;

    public ApiErrorModel(int status, String message) {
        this.status= status;
        this.message = message;
    }
}