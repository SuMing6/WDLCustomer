package com.example.customer.util;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {
    OkHttpClient okHttpClient ;
    static RetrofitUtil retrofitUtil ;
    private Retrofit retrofit;


    private RetrofitUtil(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("数据", message);
                //Log.e("获取登录状态", String.valueOf(LogingActivity.sid));
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .build();
                        return chain.proceed(request);
                    }
                })
                .addNetworkInterceptor(interceptor)
                .build();
    }

    public static synchronized RetrofitUtil getRetrofitUtil(){
        if (retrofitUtil == null){
            retrofitUtil = new RetrofitUtil();
        }
        return retrofitUtil;
    }

    public Retrofit retrofit(){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl("http://waimai.xiaoyingwu.cn/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public <T>T getRetrofit(Class<T> tClass){
        return retrofit().create(tClass);
    }
}

