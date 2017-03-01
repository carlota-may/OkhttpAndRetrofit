package com.zxm.okhttp;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface OkhttpService {
    @FormUrlEncoded
    @POST("signup")
    Call<BaseUserInfo> signUp(@Field("email") String mail,
                               @Field("password") String password);
}
