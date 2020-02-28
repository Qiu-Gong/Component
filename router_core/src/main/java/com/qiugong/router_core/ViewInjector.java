package com.qiugong.router_core;

import android.app.Activity;

public class ViewInjector {
    private static final String SUFFIX = "$$ViewInject";

    public static void injectView(Activity activity) {
        ViewInject<Activity> proxyActivity = findProxyActivity(activity);
        if (proxyActivity != null) {
            proxyActivity.inject(activity);
        }
    }

    private static <T> ViewInject<T> findProxyActivity(T object) {
        try {
            Class clazz = object.getClass();
            Class injectorClazz = Class.forName(clazz.getName() + SUFFIX);
            return (ViewInject<T>) injectorClazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
