package com.bawei.liuqi20191209.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author : Eaves
 * desc   : 功能描述
 * date   : 2019/12/9
 */
@Entity
public class UriBean {

    String json;

    @Generated(hash = 1617266855)
    public UriBean(String json) {
        this.json = json;
    }

    @Generated(hash = 390619673)
    public UriBean() {
    }

    public String getJson() {
        return this.json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
