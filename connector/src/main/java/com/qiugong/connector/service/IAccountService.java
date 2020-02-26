package com.qiugong.connector.service;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * @author qzx 20/2/26.
 */
public interface IAccountService {

    /**
     * 是否已经登录
     */
    boolean isLogin();

    /**
     * 获取登录用户的 AccountId
     */
    String getAccountId();

    /**
     * 创建 UserFragment
     */
    Fragment newUserFragment(Activity activity, int containerId, FragmentManager manager, Bundle bundle, String tag);
}