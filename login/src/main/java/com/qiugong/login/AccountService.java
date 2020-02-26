package com.qiugong.login;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.qiugong.connector.service.IAccountService;

/**
 * @author qzx 20/2/26.
 */
public class AccountService implements IAccountService {
    /**
     * 是否已经登录
     */
    @Override
    public boolean isLogin() {
        return AccountUtils.userInfo != null;
    }

    /**
     * 获取登录用户的 AccountId
     */
    @Override
    public String getAccountId() {
        return AccountUtils.userInfo == null ? null : AccountUtils.userInfo.getAccountId();
    }

    /**
     * 创建 UserFragment
     */
    @Override
    public Fragment newUserFragment(Activity activity, int containerId, FragmentManager manager, Bundle bundle, String tag) {
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment userFragment = new UserFragment();
        transaction.add(containerId, userFragment, tag);
        transaction.commit();
        return userFragment;
    }
}
