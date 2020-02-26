package com.qiugong.component;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.qiugong.base.AppConfig;
import com.qiugong.base.BaseApp;

/**
 * @author qzx 20/2/26.
 */
public class MainApplication extends BaseApp {

    @Override
    public void onCreate() {
        super.onCreate();

        if (isDebug()) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);

        initModuleApp(this);
        initModuleData(this);
    }

    private boolean isDebug() {
        return BuildConfig.DEBUG;
    }

    @Override
    public void initModuleApp(Application application) {
        for (String moduleApp : AppConfig.moduleApps) {
            try {
                Class clazz = Class.forName(moduleApp);
                BaseApp baseApp = (BaseApp) clazz.newInstance();
                baseApp.initModuleApp(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initModuleData(Application application) {
        for (String moduleApp : AppConfig.moduleApps) {
            try {
                Class clazz = Class.forName(moduleApp);
                BaseApp baseApp = (BaseApp) clazz.newInstance();
                baseApp.initModuleData(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
