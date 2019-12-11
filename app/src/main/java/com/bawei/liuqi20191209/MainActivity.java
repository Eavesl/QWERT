package com.bawei.liuqi20191209;

import android.graphics.MaskFilter;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bawei.liuqi20191209.adapter.ShopCartAdapter;
import com.bawei.liuqi20191209.app.MyApp;
import com.bawei.liuqi20191209.base.BaseActivity;
import com.bawei.liuqi20191209.base.BasePresenter;
import com.bawei.liuqi20191209.bean.ShopCartBean;
import com.bawei.liuqi20191209.bean.UriBean;
import com.bawei.liuqi20191209.dao.UriBeanDao;
import com.bawei.liuqi20191209.presenter.PresenterImpl;
import com.bawei.liuqi20191209.urls.MyUrls;
import com.bawei.liuqi20191209.utils.NetUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.all_check)
    CheckBox allCheck;
    @BindView(R.id.all_num)
    TextView allNum;
    @BindView(R.id.all_price)
    TextView allPrice;
    @BindView(R.id.all_end)
    TextView allEnd;
    private List<ShopCartBean.OrderDataBean> mList;
    private ShopCartAdapter mShopCartAdapter;
    private UriBeanDao mUriBeanDao;

    @Override
    protected void startCoding() {



        //判断有无网
        if (NetUtils.getInstance().hasNet(this)) {
            //有网

            //发起网络请求
            mPresenter.startGetNoParamsRequest(MyUrls.SHOP_CART_URL,ShopCartBean.class);

        }else {

            //无网
            List<UriBean> uriBeans = mUriBeanDao.loadAll();

            if(uriBeans != null && uriBeans.size() > 0){

                //开始
                String json = uriBeans.get(0).getJson();

                ShopCartBean shopCartBean = new Gson().fromJson(json, ShopCartBean.class);

                mShopCartAdapter.setData(shopCartBean.getOrderData());

            }

        }



    }

    @Override
    protected BasePresenter initPresenter() {
        return new PresenterImpl();
    }

    @Override
    protected void initListener() {

        mUriBeanDao = MyApp.getDaoSession().getUriBeanDao();

        mList = new ArrayList<>();
        mShopCartAdapter = new ShopCartAdapter(this, mList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(mShopCartAdapter);


        mShopCartAdapter.setAdapterCallBack(new ShopCartAdapter.AdapterCallBack() {
            @Override
            public void fatherCallBack(int fatherIndex) {

                //父
                boolean groupChecked = mShopCartAdapter.isGroupChecked(fatherIndex);//获取父
                //切换
                mShopCartAdapter.setGroupChecked(fatherIndex,groupChecked);
                boolean allChecked = mShopCartAdapter.isAllChecked();//获取
                allCheck.setChecked(allChecked);
                //计算
                calculate();



            }

            @Override
            public void childCallBack(int fatherIndex, int childIndex) {

                //子
                //设置
                mShopCartAdapter.setChildChecked(fatherIndex,childIndex);
                boolean allChecked = mShopCartAdapter.isAllChecked();//获取
                allCheck.setChecked(allChecked);//切换
                //计算
                calculate();


            }

            @Override
            public void addOrRemoveCallBack() {

                //加减器
                calculate();

            }
        });

    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onSuccess(Object o, String json) {


        if (o instanceof ShopCartBean){

            mShopCartAdapter.setData(((ShopCartBean) o).getOrderData());

            //数据库
            List<UriBean> uriBeans = mUriBeanDao.loadAll();
            if (uriBeans != null && uriBeans.size()<1){
                //存储数据库
                UriBean uriBean = new UriBean();
                uriBean.setJson(json);
                //添加
                mUriBeanDao.insert(uriBean);

            }

        }

    }

    @Override
    public void onError(String error) {

        Log.e("MyMessage",error);

    }



    @OnClick(R.id.all_check)
    public void onViewClicked() {

        //总CheckBox单击
        boolean allChecked = mShopCartAdapter.isAllChecked();//获取
        mShopCartAdapter.setAllChecked(allChecked);//切换

        calculate();


    }

    //计算
    public void calculate(){
        int count = mShopCartAdapter.calculateAllCount();
        double price = mShopCartAdapter.calculateAllPrice();

        allNum.setText("总量:"+count);
        allPrice.setText("总价为:￥"+price);

        mShopCartAdapter.notifyDataSetChanged();

    }
}
