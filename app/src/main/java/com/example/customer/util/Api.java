package com.example.customer.util;


import com.facebook.cache.disk.DefaultDiskStorage;

import java.io.File;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface Api {

    @GET()
    Observable<ResponseBody> requestGet(@Url String url, @QueryMap Map<String, String> map);

    @FormUrlEncoded
    @POST()
    Observable<ResponseBody> requestPost(@Url String url, @FieldMap Map<String, String> map);

    @POST()
    @Multipart
    Observable<ResponseBody> requestTPost(@Url String url, @FieldMap Map<String, String> map ,@Part MultipartBody.Part file);

}
