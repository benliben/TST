package com.example.benben.tst.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by benben on 2016/6/7.
 * 组织机构
 */
public class Organization implements Serializable {
    long id;//id
    String org_name;//名称
    long parent_id;//上级id
    int enable;//是否可用
    String memo;//备注
    long area_id;//区域id
    String area_name;

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }

    String org_type;//组织类型（办事处/区域 标识）
    int have_stock;//是否有仓库
    String fullpath;//层级关系
    int user_count;//用户数量
    String org_code;//编码

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrg_name() {
        return org_name;
    }

    public void setOrg_name(String org_name) {
        this.org_name = org_name;
    }

    public long getParent_id() {
        return parent_id;
    }

    public void setParent_id(long parent_id) {
        this.parent_id = parent_id;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public long getArea_id() {
        return area_id;
    }

    public void setArea_id(long area_id) {
        this.area_id = area_id;
    }

    public String getOrg_type() {
        return org_type;
    }

    public void setOrg_type(String org_type) {
        this.org_type = org_type;
    }

    public int getHave_stock() {
        return have_stock;
    }

    public void setHave_stock(int have_stock) {
        this.have_stock = have_stock;
    }

    public String getFullpath() {
        return fullpath;
    }

    public void setFullpath(String fullpath) {
        this.fullpath = fullpath;
    }

    public int getUser_count() {
        return user_count;
    }

    public void setUser_count(int user_count) {
        this.user_count = user_count;
    }

    public String getOrg_code() {
        return org_code;
    }

    public void setOrg_code(String org_code) {
        this.org_code = org_code;
    }


    public JSONObject toJsonObject() {
        JSONObject json = new JSONObject();
        try {
            json.put("area_id", getArea_id());
            json.put("area_name", getArea_name());
            json.put("enable", getEnable());
            json.put("fullpath", getFullpath());
            json.put("have_stock", getHave_stock());
            json.put("id", getId());
            json.put("memo", getMemo());
            json.put("org_name", getOrg_name());
            json.put("org_type", getOrg_type());
            json.put("parent_id", getParent_id());
            json.put("user_count", getUser_count());
            json.put("org_code", getOrg_code());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
}
