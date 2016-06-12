package com.example.benben.tst.http;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.example.benben.tst.App;
import com.example.benben.tst.ui.activity.LoginActivity;
import com.example.benben.tst.util.ToastUtil;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by benben on 2016/6/8.
 * 异步Http通信的工具
 */
public class Http {


    static boolean DEAL(Activity context, String s, EventFinish eveok,
                        boolean showOK, boolean autofinish, boolean showLoading) {
        JSONObject root = null;
        if (s == null || s == "" || s == "-1") {
            if (context != null) {
                ToastUtil.show("服务器错误");
            } else {
                try {
                    root = new JSONObject(s);
                    int r = root.getInt("code");

                    if (r == 1) {

                    } else if (r == -5) {
                        ToastUtil.show(root.getString("msg"));
                        reLogin(context);
                        root = null;
                    } else {
                        ToastUtil.show(root.getString("msg"));
                        root = null;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    root = null;
                }
            }

            if (eveok != null) {
                try {
                    eveok.onFinish(root);
                } catch (Exception e) {
                    if (context != null) {
                        ToastUtil.show("解析异常");
                        e.printStackTrace();
                    }
                }
            }
            if (showOK && root != null) {
                ToastUtil.show("操作成功");
            }
            if (showLoading)
                LoadDialog.Hide();
            if (autofinish && root != null && context != null) {
                context.finish();
            }
            if (root != null) {
                root = null;
            }
        }
        return true;
    }


    static void reLogin(Activity context) {
        Intent i = new Intent(context, LoginActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(i);
        context.finish();
    }

    static void reLogin() {
        Intent i = new Intent(App.getInstance().getApplicationContext(), LoginActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        App.getInstance().getApplicationContext().startActivity(i);
    }

    public static void POST(final Activity context, final String url,
                            final ContentValues vs, final EventFinish eveok) {
        if (showLoading && context != null)
            LoadDialog.Show(context);
        final HttpData data = new HttpData();
        final Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                DEAL(context, data.data, eveok, showok, autofinish, showLoading);
                data.data = "";
                return true;
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<BasicNameValuePair> params = new ArrayList<>();
                Iterator<?> iter = vs.valueSet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    if (entry.getValue() instanceof Integer) {
                        if (entry.getValue().equals(0)) {
                            params.add(new BasicNameValuePair(entry.getKey()
                                    .toString(),
                                    entry.getValue() == null ? null : entry
                                            .getValue().toString()));
                        } else {
                            params.add(new BasicNameValuePair(entry.getKey()
                                    .toString(), entry.getValue().toString()));
                        }
                    } else {
                        params.add(new BasicNameValuePair(entry.getKey().toString(),
                                entry.getValue() == null ? null : entry.getValue().toString()));
                    }
                }
                String uri = CHECKURL(url);
                data.data = HttpConnet.doPost(uri, params);
                handler.sendEmptyMessage(0);
            }
        }).start();
    }

    /**核对URL*/
    private static String CHECKURL(String url) {
        if (url.indexOf("http") == 0) {
            return url;
        }
        return NetWork.HTTP_ROOT+url;
    }


    /**数据*/
    private static class HttpData {
        public String data;

        public HttpData() {
            this.data = "";
        }

        public HttpData(String s) {
            this.data = s;
        }
    }
}
