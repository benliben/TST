package com.example.benben.tst.model;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

/**
 * Created by benben on 2016/6/7.
 * 主菜单中菜单按钮
 */
public class MainItemModel {
    private Context mContext;//上下文环境
    private boolean havaNum;//是否包含右上角的提示数字
    private TaskFactory task;//异步更新右上角数字的任务
    private String name;//该菜单的名字
    private Intent intent;//该菜单所跳转的页面
    private int img;//该菜单所对应的图标

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public boolean isHavaNum() {
        return havaNum;
    }

    public void setHavaNum(boolean havaNum) {
        this.havaNum = havaNum;
    }

    public TaskFactory getTask() {
        return task;
    }

    public void setTask(TaskFactory task) {
        this.task = task;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    private static interface TaskFactory {
        public AsyncTask buildTask();
    }
}
