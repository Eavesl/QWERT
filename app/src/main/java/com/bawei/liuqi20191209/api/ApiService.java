package com.bawei.liuqi20191209.api;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * author : Eaves
 * desc   : 功能描述
 * date   : 2019/12/9
 */
public interface ApiService {

    @GET
    Observable<ResponseBody> doGetNoParams(@Url String url);
}
