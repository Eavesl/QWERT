package com.bawei.practicetwo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bawei.practicetwo.contract.IContract;

/**
 * author : Eaves
 * desc   : 功能描述
 * date   : 2019/12/9
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IContract.IView {

    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (provideLayoutId() != 0){

            //加载contentView
            setContentView(provideLayoutId());

            //初始化控件
            initViews();

            //初始化P层
            mPresenter = initPresenter();

            //绑定V层实例
            if (mPresenter != null) {
                mPresenter.onAttach(this);
            }

            //初始化数据
            initData();

        }

    }

    protected abstract void initData();

    protected abstract P initPresenter();

    protected abstract void initViews();

    protected abstract int provideLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //防止内存泄露
        if (mPresenter != null) {
            mPresenter.onDetach();
            mPresenter = null;
        }

    }
}
