package com.qiugong.login;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

@Route(path = "/login/login")
public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private TextView tvState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvState = findViewById(R.id.tv_login_state);
        updateLoginState();

        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountUtils.userInfo = new UserInfo("10086", "Admin");
                updateLoginState();
            }
        });

        findViewById(R.id.btn_exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountUtils.userInfo = null;
                updateLoginState();
            }
        });

        findViewById(R.id.btn_login_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/share/share").withString("share_content", "分享数据到微博").navigation();
            }
        });
    }

    private void updateLoginState() {
        tvState.setText("这里是登录界面：" + (AccountUtils.userInfo == null ? "未登录" : AccountUtils.userInfo.getUserName()));
    }
}
