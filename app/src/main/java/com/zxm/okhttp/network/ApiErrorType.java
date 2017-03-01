package com.zxm.okhttp.network;

import android.content.Context;
import android.support.annotation.StringRes;

import com.zxm.okhttp.R;

import lombok.Getter;


public enum ApiErrorType {
//    you can add error type
    BAD_GATEWAY(500, R.string.service_error),
    NOT_FIND(404, R.string.not_found),
    NETWORK_NOT_CONNECT(300, R.string.network_wrong),
    CONNECTION_TIMEOUT(408, R.string.timeout),
    UNEXPECTED_ERROR(301, R.string.parse_error);

    @Getter
    private final int code;
    private final int messageId;
    private static final int DEFAULT_CODE = 900;

    public ApiErrorModel getApiErrorModel() {
        Context context = ApiClient.getInstance().getContext();
        return new ApiErrorModel(DEFAULT_CODE, context.getString(messageId));
    }

    ApiErrorType(int code, @StringRes int messageId) {
        this.code = code;
        this.messageId = messageId;
    }
}
