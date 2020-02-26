package com.qiugong.connector.service;

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
}
