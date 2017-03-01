package com.zxm.okhttp.entiy;

import lombok.Data;

@Data
public class BaseUserInfo {
    private int status;
    private UserInfo userInfo;
    private String message;
}
