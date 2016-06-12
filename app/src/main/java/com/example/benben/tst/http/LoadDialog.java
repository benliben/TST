package com.example.benben.tst.http;

import android.app.Activity;
import android.content.Context;


import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.example.benben.tst.R;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by benben on 2016/6/8.
 * 异步提示对话框
 */
public class LoadDialog {
    private static MaterialDialog dialog;
    private static AtomicInteger count = new AtomicInteger(0);

    public static void Show(Context context,String title,String ds,int icon,
                            final  EventFinish event) {
        count.getAndIncrement();
        if (dialog != null)
            return;

        if (title == null)
            title = "提示";

        if (icon<=0)
            icon=android.R.drawable.ic_dialog_info;

        if (ds==null)
            ds="正在加载数据，请稍后...";

        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (activity.isFinishing()) {
                dialog = null;
                return;
            }
        }

        dialog=new MaterialDialog.Builder(context).theme(Theme.LIGHT).backgroundColorRes(R.color.dialog_bg)
                .title(title)
                .titleColorRes(R.color.dialog_title)
                .content(ds)
                .contentColorRes(R.color.dialog_message)
                .progress(true,0)
                .show();
    }

    public static void Show(Context context){
        Show(context, null, null, 0, null);
    }

    public static void Show(Context context, String msg) {
        Show(context, null, msg, 0, null);
    }

    public static void Hide() {
        try {
            count.getAndDecrement();
            if (count.get() <= 0) {
                count.set(0);
                if (dialog != null) {
                    dialog.dismiss();
                    dialog = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
