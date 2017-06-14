package com.welive.thridsupport.retrofit;


import android.util.Log;

import com.welive.base.MyApplication;
import com.welive.util.L;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 自己封装的一个关于Retrofit的调用问题，以便在开发时候的测试以及输出
 * Created by welive on 2017/6/13.
 */

public class BaseCallBack<T> implements Callback<T> {

    private baseCallBack<T> baseCallBack;
    public BaseCallBack(baseCallBack<T> baseCallBack) {
        this.baseCallBack = baseCallBack;

    }

    private static final String TAG = MyApplication.getInstance().getClass().getName();
    @Override
    public void onResponse(Call<T> call, Response<T> response) {

        Log.w(TAG, "onResponse:http://7xk9dj.com1.z0.glb.clouddn.com/refreshlayout/api/defaultdata6.json "+"/n");
        Log.i(TAG, "onResponse:call.request().url() "+call.request().url());
        Log.w(TAG, "onResponse: GET" );
        Log.i(TAG, "onResponse:call.request().method() "+call.request().method() );
        Log.i(TAG, "onResponse:call.request().body() "+call.request().body());
        Log.e(TAG, "onResponse: "+response.isSuccessful()+""+response.body().toString());
//
        if(response.isSuccessful()){
            response.headers().get("host");
            baseCallBack.onSuccess(call,response);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        L.i(TAG,t.toString());
    }

    public interface baseCallBack<T>{
        void onSuccess(Call<T> call, Response<T> response);
    }


}
