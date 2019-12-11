package com.bawei.practicetwo.presenter;

import com.bawei.practicetwo.base.BasePresenter;
import com.bawei.practicetwo.contract.IContract;
import com.bawei.practicetwo.model.ModelImpl;

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
    public void startGetRequest(String url, Class cls) {

        mModel.doGet(url, cls, new IContract.ModelCallBack() {
            @Override
            public void onSuccess(Object o) {


                if (getV() != null) {
                    getV().onSuccess(o);
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
