package com.example.benben.tst.ui.activity;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Path;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.benben.tst.App;
import com.example.benben.tst.R;
import com.example.benben.tst.http.EventFinish;
import com.example.benben.tst.http.Http;
import com.example.benben.tst.http.NetWork;
import com.example.benben.tst.model.UserInfo;
import com.example.benben.tst.service.CoreService;
import com.example.benben.tst.service.ServiceUtil;
import com.example.benben.tst.util.JSONParser;
import com.example.benben.tst.util.MD5;
import com.example.benben.tst.util.SharedPreferencesUtil;
import com.example.benben.tst.util.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by benben on 2016/6/7.
 * 登录界面
 */
public class LoginActivity extends BaseActivity {
    @InjectView(R.id.login_username)
    EditText mUsername;
    @InjectView(R.id.login_password)
    EditText mPassword;
    @InjectView(R.id.login_remember)
    CheckBox mRemember;
    @InjectView(R.id.login_autologin)
    CheckBox mAutologin;
    @InjectView(R.id.login_login)
    Button mLogin;

    String username,password;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        initView();
    }

    private void initView() {

        /**记住所选择的（记住密码&&自动登录）*/
        mRemember.setChecked(SharedPreferencesUtil.getBoolean(SharedPreferencesUtil.SP_REMEMBER));
        mAutologin.setChecked(SharedPreferencesUtil.getBoolean(SharedPreferencesUtil.SP_AUTOLOGIN));
        if (mRemember.isChecked()) {
            mPassword.setText(App.getUserInfo().getPassword());//
            mPassword.setSelection(mPassword.getText().toString().length());//
            mPassword.setFocusable(true);//是否接收焦点
            mPassword.setFocusableInTouchMode(true);//
            mPassword.requestFocus();//
        }
        if (mAutologin.isChecked()) {
            username = mUsername.getText().toString().trim();
            password = mPassword.getText().toString().trim();
            if (username.equals("") || password.equals("")) {
                Toast.makeText(this,"请输入用户名和密码",Toast.LENGTH_SHORT).show();
            }else {
                login();
            }
        }

        mUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (SharedPreferencesUtil.getBoolean(SharedPreferencesUtil.SP_REMEMBER)) {
                    if (App.getUserInfo() != null) {
                        if (!s.toString().trim().equals(App.getUserInfo().getUser_account())) {
                            if (mPassword.getText().toString().trim().equals(App.getUserInfo().getPassword())) {
                                mPassword.setText("");
                            }
                        }
                    }
                }
            }
        });

    }


    @OnClick({R.id.login_remember, R.id.login_autologin, R.id.login_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_remember:
                mRemember.setChecked(true);
                break;
            case R.id.login_autologin:
                mAutologin.setChecked(false);
                break;
            case R.id.login_login:
                login();
                break;
        }
    }



    private void login() {
        if (verification()){
            ContentValues vs = new ContentValues();//ContentValues是一种存储机制，只能存储一下基本类
            vs.put("B_username",username);
            if (SharedPreferencesUtil.getBoolean(SharedPreferencesUtil.SP_REMEMBER)) {
                if (password.equals(App.getUserInfo().getPassword())) {
                    password = App.getUserInfo().getPassword();
                }else {
                    password = MD5.stringToMD5(password);
                }
            }else {
                password = MD5.stringToMD5(password);
            }

            vs.put("B_password", password);
            TelephonyManager mTMger = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            vs.put("imsi", mTMger.getDeviceId());

            Http.POST(this, NetWork.HTTP_ROOT + NetWork.PHONE_LOGIN, vs, new EventFinish() {
                @Override
                public void onFinish(Object obj) {
                    if (obj != null) {

                        try {
                            JSONObject object = (JSONObject) obj;
                            if (object.getInt("code") == 1) {
                                JSONObject jsob = object.getJSONArray("data").getJSONObject(0);
                                UserInfo info = JSONParser.parse(jsob, UserInfo.class);
                                info.setUser_account(username);
                                info.setPassword(password);
                                if (ServiceUtil.isWorked(
                                        LoginActivity.this,
                                        "com.example.benben.tst.service.CoreService"
                                )){
                                    CoreService.removeAllTag();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

        }

    }

    private boolean verification() {
        username = mUsername.getText().toString().trim();
        if (username.equals("")) {
            ToastUtil.show(this, "用户名不能为空，请输入");
            return false;
        }
        password = mPassword.getText().toString().trim();
        if (password.equals("")) {
            ToastUtil.show(this,"密码不能为空，请输入");
            return false;
        }
        return true;
    }
}
