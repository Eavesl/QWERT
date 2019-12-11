package com.bawei.practicetwo.model;

import com.bawei.practicetwo.contract.IContract;
import com.bawei.practicetwo.utils.NetUtils;

import java.util.Map;

/**
 * author : Eaves
 * desc   : 功能描述
 * date   : 2019/12/9
 */
public class ModelImpl implements IContract.IModel {
    //Get无参
    @Override
    public void doGet(String url, Class cls, final IContract.ModelCallBack modelCallBack) {

        NetUtils.getInstance().doGet(url, cls, new NetUtils.NetCallback() {
            @Override
            public void success(Object o) {

                modelCallBack.onSuccess(o);
            }

            @Override
            public void erry(String err) {

                modelCallBack.onError(err);
            }
        });

    }

    //Get有参
    @Override
    public void doGetParams(String url, Map<String, String> map, Class cls, IContract.ModelCallBack modelCallBack) {

    }
}
