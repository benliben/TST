package com.example.benben.tst.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.benben.tst.App;


public class ToastUtil {
	public static String history_msg = "";
	public static long oldtime = 0;
	public final static String ACTIVITY_PARAMS_ERROR = "参数错误";
	public final static String OPERATION_SUCCESS = "操作成功";

	public static void show(String msg) {
		show(App.getInstance().getApplicationContext(), msg);
	}

	public static void show(Context context, @NonNull String msg) {
		if (history_msg.equals(msg)) {
			if (System.currentTimeMillis() - oldtime < 1500) {
				return;
			}
		}
		if (msg==null) {
			msg = "";
		}
		try {
			Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
//			Logs.e(msg);
		}
		oldtime = System.currentTimeMillis();
		setHistory_msg(msg);
	}

	public static void setHistory_msg(@NonNull String history_msg) {
		ToastUtil.history_msg = history_msg;
	}

	public static void showActivityParamsError(Context context) {
		if (history_msg.equals(ACTIVITY_PARAMS_ERROR)) {
			if (System.currentTimeMillis() - oldtime < 1500) {
				return;
			}
		}
        Toast.makeText(context, ACTIVITY_PARAMS_ERROR, Toast.LENGTH_SHORT).show();
		oldtime = System.currentTimeMillis();
		history_msg = ACTIVITY_PARAMS_ERROR;
	}

	public static void showOperationSuccess(Context context) {
		if (history_msg.equals(OPERATION_SUCCESS)) {
			if (System.currentTimeMillis() - oldtime < 1500) {
				return;
			}
		}
		Toast.makeText(context, OPERATION_SUCCESS, Toast.LENGTH_SHORT).show();
		oldtime = System.currentTimeMillis();
		history_msg = OPERATION_SUCCESS;
	}
}
