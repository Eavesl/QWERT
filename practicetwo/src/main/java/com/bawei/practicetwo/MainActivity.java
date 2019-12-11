package com.bawei.practicetwo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.Toast;

import com.bawei.practicetwo.base.BaseActivity;
import com.bawei.practicetwo.base.BasePresenter;
import com.bawei.practicetwo.bean.JsonBean;
import com.bawei.practicetwo.presenter.PresenterImpl;
import com.bawei.practicetwo.url.Myurl;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {


    private Button mButton;
    private List<JsonBean.OrderDataBean> mList;

    @Override
    protected void initData() {



    }

    @Override
    protected BasePresenter initPresenter() {
        return new PresenterImpl();
    }

    @Override
    protected void initViews() {

        mButton = findViewById(R.id.btn);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //发起网络请求
                mPresenter.startGetRequest(Myurl.HOME_TOW, JsonBean.class);

            }
        });

    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onSuccess(Object o) {

        if (o instanceof JsonBean) {
            int code = ((JsonBean) o).getCode();
            Toast.makeText(this, code + "", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onError(String error) {

        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}
