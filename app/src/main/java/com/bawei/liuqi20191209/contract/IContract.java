package com.bawei.liuqi20191209.contract;

/**
 * author : Eaves
 * desc   : MVP契约类
 * date   : 2019/12/9
 */
public interface IContract {

    //M层回调
    interface ModelCallBack<T>{
        void onSuccess(T t,String json);
        void onError(String error);
    }

    interface IModel{
        void doGet(String url,Class cls,ModelCallBack modelCallBack);
    }


    interface IPresenter{
        void startGetNoParamsRequest(String url,Class cls);
    }


    interface IView<T>{
        void onSuccess(T t,String json);
        void onError(String error);
    }
}
