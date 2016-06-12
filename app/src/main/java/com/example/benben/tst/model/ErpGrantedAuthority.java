/**
 * @filename ErpGrantedAuthority.java    2014/10/23
 * @project erp 川喜集团ERP系统 V0.1
 * Copyright (c) 2014 四川川喜实业集团有限公司 版权所有
 * All right reserved. 
 */
package com.example.benben.tst.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 权限配置；
 * 权限字符串组成表示为:ROLE_(org_id)_(role_code); @see method initAuthority();
 * @author liushuai
 * @version 1.0.1   2014/10/23.
 */
public class ErpGrantedAuthority implements Serializable,Comparable<ErpGrantedAuthority> {
    private String rolePrefix = "ROLE_";
    private String authority;
    private int is_main;

    private Organization org;
    private Role role;

    public String getRolePrefix() {
        return rolePrefix;
    }

    public void setRolePrefix(String rolePrefix) {
        this.rolePrefix = rolePrefix;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public void setOrg(Organization org) {
        this.org = org;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public ErpGrantedAuthority() {

    }

    private void initAuthority() {
        authority = rolePrefix+org.getId()+"_"+role.getRole_code();
    }

    public Organization getOrg() {
        return org;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public int hashCode() {
        return this.getAuthority().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        ErpGrantedAuthority grantedAuthority = (ErpGrantedAuthority)obj;
        if(this.getAuthority().equals(grantedAuthority.getAuthority())) {
            return true;
        }

        return false;
    }

    public String getAuthority() {
        return authority;
    }


    public String getAttribute() {
        return getAuthority();
    }

    public int getIs_main() {
        return is_main;
    }

    public void setIs_main(int is_main) {
        this.is_main = is_main;
    }

    @Override
    public String toString() {
        return org.getOrg_name()+role.getRole_name();
    }

    @Override
    public int compareTo(ErpGrantedAuthority another) {
        if (getIs_main()==1) {
            return -1;
        }
        if (another.getIs_main()==1) {
            return 1;
        }
        return 0;
    }

    public JSONObject toJsonObject() {
        JSONObject json = new JSONObject();
        try {
            json.put("authority", getAuthority());
            json.put("is_main",getIs_main());
            json.put("org",getOrg().toJsonObject());
            json.put("role",getRole().toJsonObject());
        } catch (Exception e) {

        }
        return json;
    }

    public static JSONArray toJsonArray(ArrayList<ErpGrantedAuthority> list) {
        JSONArray array = new JSONArray();
        if (list!=null) {
            for (ErpGrantedAuthority authority: list) {
                array.put(authority.toJsonObject());
            }
        }
        return array;
    }
}
