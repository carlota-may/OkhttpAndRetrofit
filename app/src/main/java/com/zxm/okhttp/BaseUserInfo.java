package com.zxm.okhttp;

import lombok.Data;

@Data
public class BaseUserInfo {
    private int status;
    private UserInfo userInfo;
    private String message;
}
