package com.welive.base;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.welive.engine.Engine;
import com.welive.util.L;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * 重写这个Application，在后面的很多情况下会用到这个类
 * 包括xutils，umeng，xg等等的基础配置
 * Created by welive on 2017/6/12.
 */

public class MyApplication extends Application{

    private static MyApplication sInstance;
    private Engine mEngine;
    //测试环境下：true 测试环境   false：生产环境
    private boolean Test = true;
    //相应的请求域名
    private static final String TestDomainName = "http://u.test.welive.net.cn/";
    private static final String ProductDomainName = "https://u.iweizhu.com.cn/";

    public void setTest(boolean test) {
        //debug打印输出的判断条件
        L.isDebug =  Test = test;
    }

    public boolean isTest() {
        return Test;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        mEngine = new Retrofit.Builder()
                //配置URL
                .baseUrl((Test)?TestDomainName:ProductDomainName)
                //配置解析时候的OkHttp，目前也只是配置两个，一个请求超时，一个读取超时，但是这个完全可以不进行配置
                .client(InitBuilder().build())
                //配置解析类
                .addConverterFactory(GsonConverterFactory.create(InitGSON()))
                //配置RxJava的方式，支持RX2的方式
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(Engine.class);
    }

    /**
     * 配置 OkHttpClient
     * @return
     */
    private OkHttpClient.Builder InitBuilder(){
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.connectTimeout(9, TimeUnit.SECONDS);
        return  builder;
    }

    /**
     * 配置Gson
     * @return
     */
    private Gson InitGSON(){
        return new GsonBuilder()
                //配置你的Gson,说明一下，由于后台给予我们字段类型统一为 String，所以也不存在什么时间的转化了
                .serializeNulls()
                //.setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();
    }


    /**
     * 获取application
     * @return
     */
    public static MyApplication getInstance() {
        return sInstance;
    }

    /**
     * 获取 mEngine
     * @return
     */
    public Engine getEngine() {
        return mEngine;
    }

    /**
     * 设置mEngine
     * @param mEngine
     */
    public void setmEngine(Engine mEngine) {
        this.mEngine = mEngine;
    }
}
