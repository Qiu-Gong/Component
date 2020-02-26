package com.qiugong.connector.empty_service;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.qiugong.connector.service.IAccountService;

/**
 * @author qzx 20/2/26.
 */
public class EmptyAccountService implements IAccountService {

    /**
     * 是否已经登录
     */
    @Override
    public boolean isLogin() {
        return false;
    }

    /**
     * 获取登录用户的 AccountId
     */
    @Override
    public String getAccountId() {
        return null;
    }

    /**
     * 创建 UserFragment
     */
    @Override
    public Fragment newUserFragment(Activity activity, int containerId, FragmentManager manager, Bundle bundle, String tag) {
        return null;
    }
}
