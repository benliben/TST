package com.example.benben.tst.util;
import android.content.Context;
import com.example.benben.tst.BuildConfig;
import com.orhanobut.logger.Logger;


/**
 * Created by benben on 2016/6/12.
 * 自定义日志
 */
public class Logs {
    private static boolean show;

    public static void init(Context context) {
        show = BuildConfig.SHOW_LOG;
    }

    public static void e(String msg) {
        if (show) {
            Logger.i(msg);
        }
    }

    public static void e(String tag, String msg) {
        if (show) {
            Logger.i(msg, msg);
        }
    }
}
