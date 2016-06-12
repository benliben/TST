package com.example.benben.tst.http;

import com.example.benben.tst.BuildConfig;

/**
 * Created by benben on 2016/6/8.
 * 所有和后台通信的接口
 */
public class NetWork {
    public final static String IP_ADDRESS;
    public final static int WEB_PORT;
    public final static int SOCKET_PORT;
    static {
        IP_ADDRESS = BuildConfig.IP_ADDRESS;
        WEB_PORT = BuildConfig.WEB_PORT;
        SOCKET_PORT = BuildConfig.SOCKET_PORT;
    }

    public final  static String HTTP_ROOT="http://"+IP_ADDRESS+":"+WEB_PORT+"/erp/";
    public final static String HTTP_HELP = "http://" + IP_ADDRESS + ":" + WEB_PORT + "/ueditorWeb/helpShow.html?help";
    public final static String PHONE_LOGIN = "phone_login";// 手机登陆

}
