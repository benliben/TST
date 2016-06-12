package com.example.benben.tst.service;

import android.app.ActivityManager;
import android.content.Context;

import java.util.ArrayList;

/**
 * Created by benben on 2016/6/12.
 * service 检查工具类
 */
public class ServiceUtil {
    /**本方判断自己的写的Service是否已经运行*/
    public static boolean isWorked(Context context,String str) {
        ActivityManager mManager = (ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
        ArrayList<ActivityManager.RunningServiceInfo> runningService = (ArrayList<ActivityManager.RunningServiceInfo>)
                mManager.getRunningServices(60);
        for (int i = 0; i < runningService.size(); i++) {
            if (runningService.get(i).service.getClassName().toString().equals(str)) {
                return true;
            }
        }
        return false;
    }
}
