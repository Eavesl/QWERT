package com.bawei.practicetwo.contract;

/**
 * author : Eaves
 * desc   : 功能描述
 * date   : 2019/12/9
 */

import java.util.Map;

/**
 * 契约类统一管理MVP三层接口
 */
public interface IContract {


    //Model回调接口
    interface ModelCallBack<T>{
        void onSuccess(T t);
        void onError(String error);
    }

    //M层
    interface IModel{
        void doGet(String url,Class cls,ModelCallBack modelCallBack);
        void doGetParams(String url, Map<String,String> map, Class cls, ModelCallBack modelCallBack);
    }

    //P层
    interface IPresenter{
        void startGetRequest(String url,Class cls);
    }

    //V层
    interface IView<T>{
        void onSuccess(T t);
        void onError(String error);
    }


}
