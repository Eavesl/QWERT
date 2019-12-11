package com.bawei.liuqi20191209.presenter;

import com.bawei.liuqi20191209.base.BasePresenter;
import com.bawei.liuqi20191209.contract.IContract;
import com.bawei.liuqi20191209.model.ModelImpl;

/**
 * author : Eaves
 * desc   : 功能描述
 * date   : 2019/12/9
 */
public class PresenterImpl extends BasePresenter {

    @Override
    protected IContract.IModel initModel() {
        return new ModelImpl();
    }

    @Override
    public void startGetNoParamsRequest(String url, Class cls) {

        mModel.doGet(url, cls, new IContract.ModelCallBack() {
            @Override
            public void onSuccess(Object o, String json) {

                //成功回调V层
                if (getV() != null) {
                    getV().onSuccess(o,json);
                }
            }

            @Override
            public void onError(String error) {
                if (getV() != null) {
                    getV().onError(error);
                }
            }
        });
    }
}
