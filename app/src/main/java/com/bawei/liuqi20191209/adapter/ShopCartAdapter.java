package com.bawei.liuqi20191209.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.liuqi20191209.R;
import com.bawei.liuqi20191209.bean.ShopCartBean;
import com.bawei.liuqi20191209.utils.GlideUtils;
import com.bawei.liuqi20191209.widget.AdderOrRemover;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * author : Eaves
 * desc   : 功能描述
 * date   : 2019/12/9
 */
public class ShopCartAdapter extends RecyclerView.Adapter<ShopCartAdapter.FatherHolder> {

    private Context mContext;
    private List<ShopCartBean.OrderDataBean> mList;
    private Unbinder mBind;
    private AdapterCallBack mAdapterCallBack;

    public ShopCartAdapter(Context context, List<ShopCartBean.OrderDataBean> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public FatherHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.shop_cart_item, viewGroup, false);
        FatherHolder fatherHolder = new FatherHolder(view);
        return fatherHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FatherHolder fatherHolder, final int i) {

        //绑定数据
        fatherHolder.shopCheck.setChecked(isGroupChecked(i));//获取该商铺的商品全选状态


        fatherHolder.shopCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //father复选框单击事件
                mAdapterCallBack.fatherCallBack(i);
            }
        });

        //店铺名字
        fatherHolder.shopName.setText(mList.get(i).getShopName());

        //Recycler
        fatherHolder.childRecycler.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        fatherHolder.childRecycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        fatherHolder.childRecycler.setAdapter(new ChildAdapter(mList.get(i).getCartlist(), i));

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class FatherHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.shop_check)
        CheckBox shopCheck;
        @BindView(R.id.shop_name)
        TextView shopName;
        @BindView(R.id.child_recycler)
        RecyclerView childRecycler;


        public FatherHolder(@NonNull View itemView) {
                super(itemView);
                mBind = ButterKnife.bind(this, itemView);
            }
        }

        public void unBind() {
            if (mBind != null) {
                mBind.unbind();
            }
        }


    class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ChildHolder> {

        private List<ShopCartBean.OrderDataBean.CartlistBean> childList;
        private int fatherIndex;

        public ChildAdapter(List<ShopCartBean.OrderDataBean.CartlistBean> childList, int fatherIndex) {
            this.childList = childList;
            this.fatherIndex = fatherIndex;
        }

        @NonNull
        @Override
        public ChildHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View view = LayoutInflater.from(mContext).inflate(R.layout.child_item_view, viewGroup, false);
            ChildHolder childHolder = new ChildHolder(view);
            return childHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull final ChildHolder childHolder, final int i) {

            //绑定数据
            childHolder.childCheck.setChecked(childList.get(i).isChecked());
            childHolder.childCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //子Check单击事件
                    mAdapterCallBack.childCallBack(fatherIndex,i);

                }
            });

            ShopCartBean.OrderDataBean.CartlistBean ss = childList.get(i);
            childHolder.childColor.setText(ss.getColor());
            childHolder.childMeal.setText(ss.getMeal());
            childHolder.childName.setText(ss.getProductName());
            childHolder.childPrice.setText("￥"+ss.getPrice());
            childHolder.mAdderOrRemover.setCount(ss.getCount());
            GlideUtils.loadImg(ss.getDefaultPic(),childHolder.childImg);
            childHolder.mAdderOrRemover.setAdderRemoverCallBack(new AdderOrRemover.AdderRemoverCallBack() {
                @Override
                public void onCallCount(int count) {

                    childList.get(i).setChecked(true);
                    childList.get(i).setCount(count);
                    mAdapterCallBack.addOrRemoveCallBack();

                }
            });

        }

        @Override
        public int getItemCount() {
            return childList.size();
        }

        public class ChildHolder extends RecyclerView.ViewHolder {

            CheckBox childCheck;
            ImageView childImg;
            TextView childName;
            TextView childColor;
            TextView childMeal;
            TextView childPrice;
            AdderOrRemover mAdderOrRemover;

            public ChildHolder(@NonNull View itemView) {
                super(itemView);
                childCheck = itemView.findViewById(R.id.child_check);
                childImg = itemView.findViewById(R.id.child_img);
                childName = itemView.findViewById(R.id.child_name);
                childColor = itemView.findViewById(R.id.child_color);
                childMeal = itemView.findViewById(R.id.child_meal);
                childPrice = itemView.findViewById(R.id.child_price);
                mAdderOrRemover = itemView.findViewById(R.id.adder_remover);


            }
        }
    }


    //判断某商铺所有商品是否全部选中状态
    public boolean isGroupChecked(int fatherIndex) {

        List<ShopCartBean.OrderDataBean.CartlistBean> cartlist = mList.get(fatherIndex).getCartlist();
        for (ShopCartBean.OrderDataBean.CartlistBean cb : cartlist) {

            //有一件商品没有被选中则全部选中状态为false
            if (!cb.isChecked()) {

                return false;
            }
        }
        return true;
    }


    //设置某商铺商品的状态
    public void setGroupChecked(int fatherIndex, boolean checked) {
        List<ShopCartBean.OrderDataBean.CartlistBean> cartlist = mList.get(fatherIndex).getCartlist();
        for (ShopCartBean.OrderDataBean.CartlistBean cb : cartlist) {

            //设置某商铺所有商品都为!checked
            cb.setChecked(!checked);
        }
    }


    //获取全部商品的全部选中状态
    public boolean isAllChecked() {

        for (ShopCartBean.OrderDataBean orderDataBean : mList) {

            for (ShopCartBean.OrderDataBean.CartlistBean cb : orderDataBean.getCartlist()) {

                if (!cb.isChecked()) {
                    return false;
                }

            }
        }
        return true;
    }


    //设置全部商品选中状态
    public void setAllChecked(boolean checked){
        for (ShopCartBean.OrderDataBean orderDataBean : mList) {

            for (ShopCartBean.OrderDataBean.CartlistBean cb : orderDataBean.getCartlist()) {

                cb.setChecked(!checked);

            }
        }
    }

    //计算选中商品数量
    public int calculateAllCount() {

        int count = 0;
        for (ShopCartBean.OrderDataBean orderDataBean : mList) {

            for (ShopCartBean.OrderDataBean.CartlistBean cb : orderDataBean.getCartlist()) {

                if (cb.isChecked()) {
                    count = cb.getCount() + count;
                }
            }
        }

        return count;
    }

    //计算选中商品的价格
    public double calculateAllPrice() {

        double price = 0;
        for (ShopCartBean.OrderDataBean orderDataBean : mList) {

            for (ShopCartBean.OrderDataBean.CartlistBean cb : orderDataBean.getCartlist()) {

                if (cb.isChecked()) {

                    int price1 = cb.getPrice();
                    int count = cb.getCount();
                    price = price + (count * price1);

                }
            }
        }

        return price;

    }

    //设置子的checked
    public void setChildChecked(int fatherIndex,int childIndex){
        //获取
        boolean checked = mList.get(fatherIndex).getCartlist().get(childIndex).isChecked();
        //设置
        mList.get(fatherIndex).getCartlist().get(childIndex).setChecked(!checked);
    }


    public void setData(List<ShopCartBean.OrderDataBean> data){

        if (data != null){
            mList.clear();
            mList.addAll(data);
            notifyDataSetChanged();
        }
    }

    public interface AdapterCallBack {
        void fatherCallBack(int fatherIndex);

        void childCallBack(int fatherIndex, int childIndex);

        void addOrRemoveCallBack();
    }

    public void setAdapterCallBack(AdapterCallBack adapterCallBack) {
        mAdapterCallBack = adapterCallBack;
    }

}
