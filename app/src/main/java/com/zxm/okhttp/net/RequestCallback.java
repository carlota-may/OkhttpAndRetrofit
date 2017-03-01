package com.zxm.okhttp.net;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class RequestCallback<T> implements Callback<T> {

    public abstract void success(T t);

    public abstract void failure(int statusCode, ApiErrorModel apiErrorModel);

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            success(response.body());
            return;
        }
//        something wrong we should deal
        if (response.code() == ApiErrorType.BAD_GATEWAY.getCode()) {
            ApiErrorModel apiErrorModel = ApiErrorType.BAD_GATEWAY.getApiErrorModel();
            failure(response.code(), apiErrorModel);
            return;
        }

        if (response.code() == ApiErrorType.NOT_FIND.getCode()) {
            ApiErrorModel apiErrorModel = ApiErrorType.NOT_FIND.getApiErrorModel();
            failure(response.code(), apiErrorModel);
            return;
        }

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        ApiErrorModel apiErrorModel;
        try {
            apiErrorModel = gson.fromJson(response.errorBody().charStream(), ApiErrorModel.class);
        } catch (JsonParseException e) {
            failure(response.code(), ApiErrorType.UNEXPECTED_ERROR.getApiErrorModel());
            return;
        }
        failure(response.code(), apiErrorModel);
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        ApiErrorType apiErrorType;
        if (t instanceof UnknownHostException) {
            // network can not connect
            apiErrorType = ApiErrorType.NETWORK_NOT_CONNECT;
        } else if (t instanceof SocketTimeoutException) {
            // time out
            apiErrorType = ApiErrorType.CONNECTION_TIMEOUT;
        } else {
            // unexpected error
            apiErrorType = ApiErrorType.UNEXPECTED_ERROR;
        }
        failure(apiErrorType.getCode(), apiErrorType.getApiErrorModel());
    }
}
