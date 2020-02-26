package com.qiugong.connector;

import com.qiugong.connector.empty_service.EmptyAccountService;
import com.qiugong.connector.service.IAccountService;

/**
 * @author qzx 20/2/26.
 */
public class ServiceFactory {

    private static ServiceFactory serviceFactory;

    private IAccountService accountService;

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        if (serviceFactory == null) {
            synchronized (ServiceFactory.class) {
                if (serviceFactory == null) {
                    serviceFactory = new ServiceFactory();
                }
            }
        }
        return serviceFactory;
    }

    public void setAccountService(IAccountService accountService) {
        this.accountService = accountService;
    }

    public IAccountService getAccountService() {
        if (accountService == null) {
            accountService = new EmptyAccountService();
        }
        return accountService;
    }
}
