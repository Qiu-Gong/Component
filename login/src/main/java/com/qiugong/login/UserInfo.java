package com.qiugong.login;

class UserInfo {
    private String accountId;
    private String userName;

    UserInfo(String accountId, String userName) {
        this.accountId = accountId;
        this.userName = userName;
    }

    String getAccountId() {
        return accountId;
    }

    void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    String getUserName() {
        return userName;
    }

    void setUserName(String userName) {
        this.userName = userName;
    }
}
