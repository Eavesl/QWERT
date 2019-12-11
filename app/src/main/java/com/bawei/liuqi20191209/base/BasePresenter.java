package com.bawei.liuqi20191209.base;

import com.bawei.liuqi20191209.contract.IContract;

import java.lang.ref.WeakReference;

/**
 * author : Eaves
 * desc   : 功能描述
 * date   : 2019/12/9
 */
public abstract class BasePresenter<V extends IContract.IView> implements IContract.IPresenter {

    protected WeakReference<V> mWeak;
    protected IContract.IModel mModel;
    public BasePresenter() {
        mModel = initModel();
    }

    protected abstract IContract.IModel initModel();

    protected void onAttach(V v){

        mWeak = new WeakReference<>(v);
    }

    protected V getV(){
        return mWeak.get();
    }


    //销毁
    protected void onDetach(){
        if (mWeak != null) {
            mWeak.clear();
            mWeak = null;
        }
        if (mModel != null) {
            mModel = null;
        }

    }
}
