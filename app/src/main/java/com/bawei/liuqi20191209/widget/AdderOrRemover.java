package com.bawei.liuqi20191209.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.liuqi20191209.R;
import com.bawei.liuqi20191209.app.MyApp;

/**
 * author : Eaves
 * desc   : 功能描述
 * date   : 2019/12/9
 */
public class AdderOrRemover extends RelativeLayout implements View.OnClickListener {
    TextView adder;
    TextView shower;
    TextView remover;
    private AdderRemoverCallBack mAdderRemoverCallBack;
    public AdderOrRemover(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.add_remove,this);

        initViews();
    }


    public void setCount(int count){

        shower.setText(count+"");
    }
    private void initViews() {

        adder = findViewById(R.id.adder);
        adder.setOnClickListener(this);
        shower = findViewById(R.id.shower);
        remover = findViewById(R.id.remover);
        remover.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        int i = Integer.parseInt(shower.getText().toString());

        switch (v.getId()){
            case R.id.adder:
                i++;

                if (mAdderRemoverCallBack != null){
                    //传给适配器count
                    shower.setText(i+"");
                    mAdderRemoverCallBack.onCallCount(i);
                }

                break;
            case R.id.remover:

                if (i > 1){
                    i--;
                    if (mAdderRemoverCallBack != null){
                        shower.setText(i+"");
                        mAdderRemoverCallBack.onCallCount(i);
                    }
                }else {
                    Toast.makeText(MyApp.mContext, "最少要买一件", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public interface AdderRemoverCallBack{
        void onCallCount(int count);
    }

    public void setAdderRemoverCallBack(AdderRemoverCallBack adderRemoverCallBack){
        mAdderRemoverCallBack = adderRemoverCallBack;
    }
}
