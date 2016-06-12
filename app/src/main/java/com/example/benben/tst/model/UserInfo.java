package com.example.benben.tst.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by benben on 2016/6/7.
 * 用户信息
 */
public class UserInfo implements Serializable {
    private long id;//ID
    private String user_name;//用户名
    private String user_account = "";//用户登录名
    private String password = "";//密码
    private int type;//类型
    private String org_name;//隶属公司
    private long parent_id;//上司的ID
    private String role_name;//角色
    private String token;
    private int index;
    String role_code;//角色代码
    String mobile = "";//移动
    int[]menus;//菜单
    int[]for_remove_menus;//删除的菜单
    boolean online;//是否在线
    long parent_user_id;//上司用户ID
    String parent_user_name;//上司用户名
    int staff_state;//员工状态

    private ArrayList<ErpGrantedAuthority> authorities;

    public ArrayList<ErpGrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(ArrayList<ErpGrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_account() {
        return user_account;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getRole_code() {
        return role_code;
    }

    public void setRole_code(String role_code) {
        this.role_code = role_code;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int[] getMenus() {
        return menus;
    }

    public void setMenus(int[] menus) {
        this.menus = menus;
    }

    public int[] getFor_remove_menus() {
        return for_remove_menus;
    }

    public void setFor_remove_menus(int[] for_remove_menus) {
        this.for_remove_menus = for_remove_menus;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public long getParent_user_id() {
        return parent_user_id;
    }

    public void setParent_user_id(long parnet_user_id) {
        this.parent_user_id = parnet_user_id;
    }

    public String getParent_user_name() {
        return parent_user_name;
    }

    public void setParent_user_name(String parent_user_name) {
        this.parent_user_name = parent_user_name;
    }

    public int getStaff_state() {
        return staff_state;
    }

    public void setStaff_state(int staff_state) {
        this.staff_state = staff_state;
    }

    public long getPid() {
        return parent_id;
    }

    public void setPid(long pid) {
        this.parent_id = pid;
    }


    public ErpGrantedAuthority getMainAuthority() {
        if (getAuthorities() != null) {
            for (ErpGrantedAuthority authority : getAuthorities()) {
                if (authority.getIs_main() == 1) {
                    return authority;

                }
            }
            if (getAuthorities().size() > 0) {
                return getAuthorities().get(0);
            }
        }
        return null;
    }

    public String getRolestring() {
        String str = "";
        if (getAuthorities() == null) {
            str = "无\n";
        }else {
            Collections.sort(getAuthorities());
            for (ErpGrantedAuthority authority : getAuthorities()) {
                str += authority.toString();
                if (authority.getIs_main() == 1) {
                    str += "(主职位)";
                }
                str += "\n";
            }
        }
        str = str.substring(0, str.length() - 1);
        return str;
    }

}
