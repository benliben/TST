package com.example.benben.tst.db;

import com.example.benben.tst.model.UserInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benben on 2016/6/7.
 */
public class AppDB {
    /**获取用户信息*/
    public List<UserInfo> parseUsers(JSONArray array){
        ArrayList<UserInfo> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            try {
                JSONObject object=array.getJSONObject(i);
                UserInfo info = new UserInfo();
                info.setId(object.getInt("id"));
                info.setUser_name(object.getString("user_name"));
                info.setOrg_name(object.getString("org_name"));
                info.setRole_name(object.getString("role_name"));
                list.add(info);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }


    /***/
}
