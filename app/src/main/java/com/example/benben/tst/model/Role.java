package com.example.benben.tst.model;

import com.example.benben.tst.data.YesOrNo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by benben on 2016/6/7.
 * 角色表
 */
public class Role implements Serializable {
    private static final  long serialCersionUID=7281494197939357492L;
    private long id;//角色ID
    private String role_name;//角色名称
    private Integer enable = YesOrNo.YES.getCode();//是否可用，0不可用，1可用
    private String memo;//备注
    private String role_code;
    int weight;
    public Role(){}

    public Role(long id) {
        this.id = id;
    }

    public Role(long id, String role_name, Integer enable, String memo, String role_code) {
        this.id = id;
        this.role_name = role_name;
        this.enable = enable;
        this.memo = memo;
        this.role_code = role_code;
    }

    public static long getSerialCersionUID() {
        return serialCersionUID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getRole_code() {
        return role_code;
    }

    public void setRole_code(String role_code) {
        this.role_code = role_code;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }


    public JSONObject toJsonObject() {
        JSONObject json = new JSONObject();
        try {
            json.put("role_code", getRole_code());
            json.put("role_name", getRole_name());
            json.put("enable", getEnable());
            json.put("id", getId());
            json.put("weight", getWeight());
            json.put("memo", getMemo());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
}
