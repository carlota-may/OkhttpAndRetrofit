package com.zxm.okhttp.network;

import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zxm.okhttp.BuildConfig;
import com.zxm.okhttp.R;

import java.util.concurrent.TimeUnit;

import lombok.Getter;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class ApiClient {

    private static final int TIMEOUT_CONNECTION = 10_000;
    private static final int TIMEOUT_READ = 20_000;
    private static ApiClient apiClient;

    @Getter
    private Context context;
    
    @Getter
    private OkhttpService okhttpService;

    private ApiClient() {
        //prevent instance
    }

    public static ApiClient getInstance() {
        if (apiClient == null) {
            apiClient = new ApiClient();
        }
        return apiClient;
    }

    public void initialize(final Context c) {
        context = c;
//        Use your header
        Interceptor interceptor = chain -> {
            Request.Builder requestBuilder = chain.request().newBuilder();
            requestBuilder.addHeader("TIME", "time");
            requestBuilder.addHeader("TOKEN", "token");
            return chain.proceed(requestBuilder.build());
        };

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.MILLISECONDS)
                .readTimeout(TIMEOUT_READ, TimeUnit.MILLISECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE))
                .build();

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
//      replace your baseUrl
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        okhttpService = retrofit.create(OkhttpService.class);
    }
}