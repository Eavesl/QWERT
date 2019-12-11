package com.bawei.practicetwo.base;

import com.bawei.practicetwo.contract.IContract;

import java.lang.ref.WeakReference;
import java.util.PriorityQueue;

/**
 * author : Eaves
 * desc   : 功能描述
 * date   : 2019/12/9
 */
public abstract class BasePresenter <V extends IContract.IView> implements IContract.IPresenter {

    /**
     * 拥有V层实例
     * 拥有M层实例
     */

    private WeakReference<V> mWeak;//防止内存泄露
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
