package com.bawei.liuqi20191209.app;

import android.app.Application;
import android.content.Context;

import com.bawei.liuqi20191209.dao.DaoMaster;
import com.bawei.liuqi20191209.dao.DaoSession;

/**
 * author : Eaves
 * desc   : 功能描述
 * date   : 2019/12/9
 */
public class MyApp extends Application {

    public static Context mContext;
    private static DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        initDataBase();
    }

    private void initDataBase() {

        mDaoSession = DaoMaster.newDevSession(this, "WuDiFo.db");
    }

    public static DaoSession getDaoSession() {
        return mDaoSession;
    }
}
