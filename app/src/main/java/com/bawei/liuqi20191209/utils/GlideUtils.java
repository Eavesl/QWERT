package com.bawei.liuqi20191209.utils;

import android.widget.ImageView;

import com.bawei.liuqi20191209.R;
import com.bawei.liuqi20191209.app.MyApp;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

/**
 * author : Eaves
 * desc   : 功能描述
 * date   : 2019/12/9
 */
public class GlideUtils {

    public static void loadImg(String uri, ImageView imageView){

        Glide.with(MyApp.mContext)
                .load(uri)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher_round)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(8)))
                .into(imageView);
    }
}
