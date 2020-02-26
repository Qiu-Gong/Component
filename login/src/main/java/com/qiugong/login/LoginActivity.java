package com.qiugong.login;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.qiugong.connector.ServiceFactory;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Log.d(TAG, "onCreate: isLogin = " + ServiceFactory.getInstance().getAccountService().isLogin());
        Log.d(TAG, "onCreate: account id = " + ServiceFactory.getInstance().getAccountService().getAccountId());
        AccountUtils.userInfo = new UserInfo("123", "QiuGong");
        Log.d(TAG, "onCreate: isLogin = " + ServiceFactory.getInstance().getAccountService().isLogin());
        Log.d(TAG, "onCreate: account id = " + ServiceFactory.getInstance().getAccountService().getAccountId());
    }
}
