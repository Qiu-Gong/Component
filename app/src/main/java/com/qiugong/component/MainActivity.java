package com.qiugong.component;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.qiugong.router_annotation.ViewById;
import com.qiugong.router_core.ViewInjector;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @ViewById
    Button login;
    @ViewById
    Button share;
    @ViewById
    Button fragment;
    @ViewById
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewInjector.injectView(this);
        textView.setText("注解生成成功");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/login/login").navigation();
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/share/share").withString("share_content", "分享数据到微博").navigation(MainActivity.this,
                        new NavCallback() {

                            @Override
                            public void onArrival(Postcard postcard) {
                                Log.d(TAG, "onArrival: " + postcard);
                            }

                            @Override
                            public void onInterrupt(Postcard postcard) {
                                Log.d(TAG, "onInterrupt: " + postcard);
                                MainActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "请先登陆", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
            }
        });

        fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FragmentActivity.class));
            }
        });
    }
}
