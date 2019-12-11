package com.bawei.liuqi20191209.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bawei.liuqi20191209.contract.IContract;
import com.bawei.liuqi20191209.utils.ScreenUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author : Eaves
 * desc   : 功能描述
 * date   : 2019/12/9
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IContract.IView {

    protected P mPresenter;
    private Unbinder mBind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (provideLayoutId() != 0){
            //状态栏透明
            ScreenUtils.immersiveWindow(getWindow());
            setContentView(provideLayoutId());

            //绑定ButterKnife
            mBind = ButterKnife.bind(this);

            //初始化监听器
            initListener();

            //初始化P层
            mPresenter = initPresenter();

            if (mPresenter != null) {
                mPresenter.onAttach(this);
            }

            //代码执行
            startCoding();
        }

    }

    protected abstract void startCoding();

    protected abstract P initPresenter();

    protected abstract void initListener();

    protected abstract int provideLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //内存泄露
        if (mPresenter != null) {
            mPresenter.onDetach();
            mPresenter = null;
        }

        //解除绑定
        if (mBind != null) {
            mBind.unbind();
            mBind = null;
        }
    }
}
