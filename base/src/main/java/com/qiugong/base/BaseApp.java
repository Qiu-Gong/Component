package com.qiugong.base;

import android.app.Application;

/**
 * @author qzx 20/2/26.
 */
public abstract class BaseApp extends Application {

    public abstract void initModuleApp(Application application);

    public abstract void initModuleData(Application application);
}
