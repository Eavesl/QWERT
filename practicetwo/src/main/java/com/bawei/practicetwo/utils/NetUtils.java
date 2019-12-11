package com.bawei.practicetwo.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.bawei.practicetwo.api.ApiService;
import com.bawei.practicetwo.url.Myurl;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
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

    public OkHttpClient mOkHttpClient;
    public Retrofit mRetrofit;
    private final ApiService mApiService;

    private NetUtils() {
        //HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
       // interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        mOkHttpClient=new OkHttpClient.Builder()
                //.addNetworkInterceptor(interceptor)
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .build();
        mRetrofit=new Retrofit.Builder()
                .baseUrl(Myurl.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(mOkHttpClient)
                .build();
           //动态代理
        mApiService = mRetrofit.create(ApiService.class);


    }
    //单例
    private static class NetHolder{

        private final static NetUtils netUtils=new NetUtils();

    }

    public static NetUtils getInstance() {
        return NetHolder.netUtils;
    }

    //回调接口
    public interface NetCallback<T>{
        //如果加入greendao就加入String json//void success(T t,String json);
        void success(T t);
        void erry(String err);

    }

    //网络判断
    public boolean getNet(Context context){
      ConnectivityManager manager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
            if (info!=null){
                return info.isConnected();
            }
            return false;
    }


    //Get无参
    public void doGet(String url, final Class cls, final NetCallback netCallback){

        mApiService.getInnofo(url).subscribeOn(Schedulers.io())//子线程发射
        .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                        Log.e("MyMessage","onSubscribe");
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {

                        try {
                            String json = responseBody.string();
                            Object o = new Gson().fromJson(json, cls);
                            netCallback.success(o);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        netCallback.erry(e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
