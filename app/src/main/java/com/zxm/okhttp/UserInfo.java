package com.zxm.okhttp;

import lombok.Data;

@Data
public class UserInfo {
    private String userId;
    private String userName;
    private String nickName;
    private String role;
    private String email;
    private String token;
    private String createdAt;
    private String updatedAt;
}
