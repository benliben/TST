package com.example.benben.tst.http;

import com.example.benben.tst.App;
import com.example.benben.tst.util.Logs;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;


import java.net.URLEncoder;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by benben on 2016/6/12.
 * 同步http通信的工具类
 */
public class HttpConnet {

    private static HttpClient httpClient = null;
    private static String LAST_URL = "";
    private static String LAST_RESULT = "";
    private static String CHARSET = "UTF-8";



    public static String doGet(String url, Map<?, ?> params) {
        String paramStr = "";
        String strResult = "-1";
        if (params != null) {
            Iterator<?> iter = params.entrySet().iterator();
            while (iter.hasNext()) {
                @SuppressWarnings("rawtypes")
                Map.Entry entry = (Map.Entry) iter.next();
                Object key = entry.getKey();
                Object val = entry.getValue();
                paramStr += "&" + key + "=" + URLEncoder.encode(val.toString());
            }
            if (!paramStr.equals("")) {
                paramStr = paramStr.replaceFirst("&", "?");
                url += paramStr;
            }
        }
        Logs.e(url);
        getHttpClient();
        HttpGet httpRequest = new HttpGet(url);
        if (App.getUserInfo() != null)
            httpRequest.addHeader("Cookie", getCookie());
        try {
            HttpResponse httpResponse = httpClient.execute(httpRequest);
            if (httpResponse.getStatusLine().getStatusCode() == 200)
                strResult = EntityUtils.toString(httpResponse.getEntity());
            else
                Logs.e("http error", "code:"
                        + httpResponse.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
            System.gc();
        }
        httpRequest.abort();
//        RequestFuture<String> future = RequestFuture.newFuture();
//        StringRequest request = new StringRequest(Request.Method.GET,url,future,future);
//        App.getInstance().getRequestQueue().add(request);
//        try {
//            strResult = future.get(); // this will block
//        } catch (InterruptedException e) {
//            // exception handling
//        } catch (ExecutionException e) {
//            // exception handling
//        }
        Logs.e("result=" + strResult);
        return strResult;
    }



    /**
     * List<NameValuePair> params = new ArrayList<NameValuePair>();
     * params.add(new BasicNameValuePair("email", "j@aiyy.org")); params.add(new
     * BasicNameValuePair("password", "300831")); doPost(url,params);
     */
    public static String doPost(final String url, final List<BasicNameValuePair> params) {
        String strResult = "-1";//结果
        getHttpClient();
        Logs.e("utl=" + url);
        StringBuilder sb = new StringBuilder();
        sb.append(url);
        HttpPost httpRequest = new HttpPost(url);
        if (params != null) {
            if (App.getUserInfo() != null) {
                httpRequest.addHeader("Cookie", getCookie());
                params.add(new BasicNameValuePair("token", App.getUserInfo().getToken()));
                params.add(new BasicNameValuePair("user_id", App.getUserInfo().getId() + ""));
            }
            params.add(new BasicNameValuePair("is_from", 1 + ""));
            Collections.sort(params, new PairComparator());
            for (NameValuePair pair : params) {
                Logs.e(pair.getName() + "=" + pair.getValue());
                sb.append(pair.getName() + "=" + pair.getValue());
            }
        }
        sb.append(System.currentTimeMillis() / 1000);
        Logs.e("LAST_URL=" + LAST_URL);

        if (LAST_URL.equals(sb.toString())) {
            if (!strResult.equals(LAST_RESULT)) {
                return LAST_URL;
            }
        }
            LAST_URL = sb.toString();
            LAST_RESULT = strResult;
            Logs.e("LAST_URL=" + LAST_URL);
            try {
                httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                HttpResponse httpResponse = httpClient.execute(httpRequest);
                if (httpResponse.getStatusLine().getStatusCode() == 200) {
                    strResult = EntityUtils.toString(httpRequest.getEntity());
                } else {
                    Logs.e("StatusCode" + httpResponse.getStatusLine().getStatusCode());
                }
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }

            httpRequest.abort();
            LAST_RESULT = strResult;
            Logs.e("result=" + strResult);
        return strResult;
    }

    public static HttpClient getHttpClient() {

        if (httpClient == null) {
            HttpParams params = new BasicHttpParams();
            /**设置一些基本参数*/
            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(params,CHARSET);
            HttpProtocolParams.setUseExpectContinue(params, true);
            HttpProtocolParams.setUserAgent(params,
                    "Mozilla/5.0(Linux;U;Android 2.2.1;en-us;Nexus One Build.FRG83) "
                            + "AppleWebKit/553.1(KHTML,like Gecko) Version/4.0 Mobile Safari/533.1");

            /**超时设置*/
        /*从链接池中链接的超时时间*/
            ConnManagerParams.setTimeout(params, 3000);
            /*连接超时*/
            HttpConnectionParams.setConnectionTimeout(params, 3000);
            /*请求超时*/
            HttpConnectionParams.setSoTimeout(params, 3000);
            HttpConnectionParams.setSocketBufferSize(params, 8192);
            HttpClientParams.setRedirecting(params, true);

            /*设置我们的HttpClient支持Http和Https两种模式*/
            SchemeRegistry schReg = new SchemeRegistry();
            schReg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            schReg.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));

            /*使用线程安全的连接管理来创建HttpClient*/
            ClientConnectionManager conMgr = new ThreadSafeClientConnManager(params, schReg);
            httpClient = new DefaultHttpClient(conMgr, params);
        }
        return httpClient;
    }

    public static String getCookie() {
        String s = "token" + App.getUserInfo().getToken()
                + "id" + App.getUserInfo().getId()
                + "index" + App.getUserInfo().getIndex();
        Logs.e(s);
        return s;
    }
}
