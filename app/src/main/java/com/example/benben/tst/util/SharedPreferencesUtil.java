package com.example.benben.tst.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.benben.tst.model.ErpGrantedAuthority;
import com.example.benben.tst.model.UserInfo;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by benben on 2016/6/7.
 * 配置文件操作类
 */
public class SharedPreferencesUtil {

    private static SharedPreferences sp;//配置文件
    public static String SP_ONLYWIFI="onlywifi";//配置参数——是否值在WiFi状态下上传文件
    public static String SP_REMEMBER = "remember";//记住
    public static String SP_AUTOLOGIN = "sutologin";//登录
    public static String SP_USER_NAME = "sp_user_name";//
    public static String SP_USER_ACCOUNT = "sp_user_account";
    public static String SP_USER_PASSWORD = "sp_user_password";
    public static String SP_USER_ID = "sp_user_id";
    public static String SP_USER_PID = "sp_user_pid";
    public static String SP_USER_AFF = "sp_user_aff";
    public static String SP_USER_MSG = "sp_user_msg";
    public static String SP_USER_TYPE = "sp_user_type";
    public static String SP_LIGHT_KEEP = "sp_light_keep";
    public static String SP_USER_TOKEN = "sp_user_token";
    public static String SP_USER_INDEX = "sp_user_index";
    public static String ENABLE_GPS = "enable_gps";
    public static String START_TIME = "start_time";
    public static String END_TIME = "end_time";
    public static String WORD_DAYS = "word_days";
    public static String WORK_START_TIME = "work_start_time";
    public static String WORK_END_TIME = "work_end_time";
    public static String IS_IMEI = "is_imei";
    public static String LATITUDE = "latitude";
    public static String LONGITUDE = "longitude";
    public static String LOCATION = "location";
    public static String GO_TO_WORK = "go_to_work";
    public static String GO_OFF_WORK = "go_off_work";
    public static String VISIT_POINT_ID = "visit_point_id";
    public static String VISIT_POINT_NAME = "visit_point_name";
    public static String VISIT_POINT_ADDRESS = "visit_point_address";
    public static String VISIT_POINT_TYPE = "visit_point_type";
    public static String VISIT_ID = "visit_id";
    public static String VISIT_USER_ID = "visit_user_id";
    public static String IS_OPEN_GPS = "is_open_gps";
    public static String IS_USE_GPS = "is_use_gps";
    public static String USER_STATE = "user_state";

    /**获取配置参数*/
    public static boolean getBoolean(String name){
        return sp.getBoolean(name, false);
    }


    /**初始化操作类*/
    public static void init(Context context) {
        sp = context.getSharedPreferences("config", 0);
    }

    public static UserInfo getUserInfo() {
        UserInfo info = new UserInfo();
        if (sp.getLong(SP_USER_ID, 0) != 0) {
            info = new UserInfo();
            info.setId(sp.getLong(SP_USER_ID, 0));
            info.setOrg_name(sp.getString(SP_USER_AFF, ""));
            info.setRole_name(sp.getString(SP_USER_MSG, ""));
            info.setPassword(sp.getString(SP_USER_PASSWORD, ""));
            info.setPid(sp.getLong(SP_USER_PID, 0));
            info.setType(sp.getInt(SP_USER_TYPE, 0));
            info.setUser_name(sp.getString(SP_USER_NAME, ""));
            info.setToken(sp.getString(SP_USER_TOKEN, ""));
            info.setIndex(sp.getInt(SP_USER_INDEX, 0));
            info.setPassword(sp.getString(SP_USER_PASSWORD, ""));
            info.setUser_account(sp.getString(SP_USER_ACCOUNT, ""));
            info.setParent_id(sp.getLong("patent_user_id", 0L));
            info.setParent_user_name(sp.getString("parent_user_name", ""));
            info.setStaff_state(sp.getInt(USER_STATE, 0));
            try {
                info.setAuthorities(JSONParser.parseArray(new JSONArray(sp.getString("list","")), ErpGrantedAuthority.class));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return info;
    }

    public static void putUserInfo(UserInfo info) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(SP_USER_NAME, info.getUser_name());
        editor.putString(SP_USER_ACCOUNT, info.getUser_account());
        editor.putLong(SP_USER_ID, info.getId());
        editor.putLong(SP_USER_PID, info.getPid());
        editor.putString(SP_USER_AFF, info.getOrg_name());
        editor.putString(SP_USER_MSG, info.getRole_name());
        editor.putInt(SP_USER_TYPE, info.getType());
        editor.putString(SP_USER_TOKEN, info.getToken());
        editor.putInt(SP_USER_INDEX, info.getIndex());
        editor.putString(SP_USER_PASSWORD, info.getPassword());
        editor.putInt(USER_STATE,info.getStaff_state());
        editor.putLong("parent_user_id", info.getParent_user_id());
        editor.putString("parent_user_name", info.getParent_user_name());
        editor.putString("list", ErpGrantedAuthority.toJsonArray(info.getAuthorities()).toString());
        editor.commit();
    }
}
