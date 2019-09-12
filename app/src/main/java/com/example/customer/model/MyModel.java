package com.example.customer.model;

import android.util.Log;

import com.example.customer.contract.MyContract;
import com.example.customer.util.Api;
import com.example.customer.util.RetrofitUtil;
import com.google.gson.Gson;

import java.io.File;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;

public class MyModel implements MyContract.MyModel {
    MyCallBack myCallBack;

    @Override
    public void doGet(String url, final Class cls, Map<String, String> map, final MyCallBack myCallBack) {
        this.myCallBack = myCallBack;
        RetrofitUtil.getRetrofitUtil().getRetrofit(Api.class)
                .requestGet(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        Gson gson = new Gson();
                        Object o = gson.fromJson(responseBody.string(), cls);
                        myCallBack.success(o);
                        Log.e("你看看+mg",o+"");
                    }
                }/*, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   throwable.printStackTrace();
                               }
                           }*/);
    }

    @Override
    public void doPost(String url, final Class aClass, Map<String, String> map, final MyCallBack myCallBack) {
        this.myCallBack = myCallBack;
        RetrofitUtil.getRetrofitUtil().getRetrofit(Api.class)
                .requestPost(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        Gson gson = new Gson();
                        Object o = gson.fromJson(responseBody.string(), aClass);
                        myCallBack.success(o);
                        Log.e("你看看+mp",o+"");
                    }
                    //  错误   io.reactivex.exceptions.OnErrorNotImplementedException: java.lang.IllegalStateException: Expected BEGIN_ARRAY but was STRING at line 1 column 32 path $.data
                }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   throwable.printStackTrace();
                               }
                           });
    }

    @Override
    public void doPTxPost(String urla, final Class cls, int id, String token , String content , MultipartBody.Part file, final MyCallBack myCallBack) {
        this.myCallBack = myCallBack;
        RetrofitUtil.getRetrofitUtil().getRetrofit(Api.class)
                .requestTPost(urla,id,token,content,file)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        Gson gson = new Gson();
                        Object o = gson.fromJson(responseBody.string(), cls);
                        myCallBack.success(o);
                        Log.e("你看看+mp",o+"");
                    }
                    //  错误   io.reactivex.exceptions.OnErrorNotImplementedException: java.lang.IllegalStateException: Expected BEGIN_ARRAY but was STRING at line 1 column 32 path $.data
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }



    public interface MyCallBack{
        void success(Object o);
        void onError(Object o);
    }
}
