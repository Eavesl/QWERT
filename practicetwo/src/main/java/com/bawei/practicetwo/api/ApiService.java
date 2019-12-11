package com.bawei.practicetwo.api;

import java.util.Map;
import java.util.ResourceBundle;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * author : Eaves
 * desc   : 功能描述
 * date   : 2019/12/9
 */
public interface ApiService {

    @GET
    Observable<ResponseBody> getInfo(@Url String url, @QueryMap Map<String,String> map);
    @GET
    Observable<ResponseBody> getInnofo(@Url String url);



}
