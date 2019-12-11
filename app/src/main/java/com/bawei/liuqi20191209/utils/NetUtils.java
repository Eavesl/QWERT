package com.bawei.liuqi20191209.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.bawei.liuqi20191209.api.ApiService;
import com.bawei.liuqi20191209.urls.MyUrls;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author : Eaves
 * desc   : 功能描述
 * date   : 2019/12/9
 */
public class NetUtils {


    private final ApiService mApiService;

    private NetUtils() {

        //日志拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //ok客户端
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10,TimeUnit.SECONDS)
                .build();

        //Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyUrls.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())//关联RxJava
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//关联Gson解析
                .build();


        mApiService = retrofit.create(ApiService.class);//代理类对象

    }

    private static class NetHolder{
        private static final NetUtils NET_UTILS = new NetUtils();
    }

    public static NetUtils getInstance() {
        return NetHolder.NET_UTILS;
    }

    public interface NetCallBack<T>{
        void onSuccess(T t,String json);
        void onError(String error);
    }


    //get请求无参
    public void doGet(String url, final Class cls, final NetCallBack netCallBack){

        mApiService.doGetNoParams(url).subscribeOn(Schedulers.io())//线程调度
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {

                        //请求成功
                        try {
                            String json = responseBody.string();

                            Object o = new Gson().fromJson(json, cls);

                            if (o != null){
                                netCallBack.onSuccess(o,json);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                        //失败
                        netCallBack.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //有无网
    public boolean hasNet(Context context){

        ConnectivityManager systemService = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = systemService.getActiveNetworkInfo();

        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {

            return true;
        }

        return false;
    }


}
