package com.qiugong.connector.empty_service;

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
}
