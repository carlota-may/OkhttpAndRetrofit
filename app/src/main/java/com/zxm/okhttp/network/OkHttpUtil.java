package com.lanjing.verificationcoderecognizer.util;


import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public final class OkHttpUtil {
    private OkHttpUtil() {
        //no instance
    }

    public static MultipartBody.Part createPartWithAllImageFormats(String requestKey, File file) {
        return MultipartBody.Part.createFormData(requestKey, file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
    }
}
