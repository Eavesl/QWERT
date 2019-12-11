package com.bawei.liuqi20191209.model;

import com.bawei.liuqi20191209.contract.IContract;
import com.bawei.liuqi20191209.utils.NetUtils;

/**
 * author : Eaves
 * desc   : 功能描述
 * date   : 2019/12/9
 */
public class ModelImpl implements IContract.IModel {
    @Override
    public void doGet(String url, Class cls, final IContract.ModelCallBack modelCallBack) {
        NetUtils.getInstance().doGet(url, cls, new NetUtils.NetCallBack() {
            @Override
            public void onSuccess(Object o, String json) {

                //成功
                modelCallBack.onSuccess(o,json);
            }

            @Override
            public void onError(String error) {

                modelCallBack.onError(error);
            }
        });
    }
}
