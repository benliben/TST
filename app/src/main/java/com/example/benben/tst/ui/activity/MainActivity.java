package com.example.benben.tst.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.benben.tst.App;
import com.example.benben.tst.R;
import com.example.benben.tst.http.EventFinish;
import com.example.benben.tst.model.MainItemModel;
import com.example.benben.tst.ui.adapter.MainAdapter;


import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.topLeft)
    ImageView mLeft;
    @InjectView(R.id.topTitle)
    TextView mTitle;
    @InjectView(R.id.topRight)
    ImageView mRight;
    @InjectView(R.id.main_content)
    RecyclerView mContent;

    private MainAdapter mAdapter;
    ArrayList<MainItemModel> menuItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initData();
    }

    private void initData() {
        mTitle.setText(App.getUserInfo().getUser_name() + ",你好!");
        mLeft.setImageResource(R.mipmap.ic_btn_mymsg);
        mRight.setImageResource(R.mipmap.ic_setpwd_n);
    }

    @OnClick({R.id.topLeft, R.id.topRight})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.topLeft:
                break;
            case R.id.topRight:
                break;
        }
    }


    /**通过buck键退出应用*/
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
//            Confirm.ShowTwo(this, "提示", "你确定哟啊退出本应用", new EventFinish() {
//                @Override
//                public void onFinish(Object obj) {
//                    if (obj != null) {
//                        App.getInstance().exit();
//                    }
//                }
//            });
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
