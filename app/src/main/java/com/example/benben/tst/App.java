package com.example.benben.tst;

import android.app.Activity;

import com.example.benben.tst.model.UserInfo;
import com.example.benben.tst.ui.activity.BaseActivity;
import com.example.benben.tst.util.SharedPreferencesUtil;

import java.util.LinkedList;

/**
 * Created by benben on 2016/6/7.
 */
public class App extends BaseActivity {
    private String TAG = "App";
    private static UserInfo userInfo;
    private LinkedList<Activity> mList = new LinkedList<>();
    private static App instance;

    public static UserInfo getUserInfo() {
        if (userInfo == null) {
            userInfo = SharedPreferencesUtil.getUserInfo();
        }
        return userInfo;
    }

    public static void setUserInfo(UserInfo info) {
        userInfo = info;
        SharedPreferencesUtil.putUserInfo(info);

    }

    public synchronized static App getInstance() {
        if (null==instance){
            instance = new App();
        }
        return instance;
    }

    public void exit() {
        for (Activity activity : mList) {
            if (activity != null && !activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
