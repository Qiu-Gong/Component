package com.qiugong.login;

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
}
