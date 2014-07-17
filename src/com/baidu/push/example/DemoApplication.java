package com.baidu.push.example;

import android.content.Intent;


import com.baidu.frontia.FrontiaApplication;
import com.baidu.push.example.module.ServicesModule;
import com.baidu.push.example.service.InitializeAppService;
import com.baidu.push.example.service.ServerStatusService;
import roboguice.RoboGuice;
import roboguice.inject.SharedPreferencesName;


/*
 * 如果您的工程中实现了Application的继承类，那么，您需要将父类改为com.baidu.frontia.FrontiaApplication。
 * 如果您没有实现Application的继承类，那么，请在AndroidManifest.xml的Application标签中增加属性： 
 * <application android:name="com.baidu.frontia.FrontiaApplication"
 * 。。。
 */
public class DemoApplication extends FrontiaApplication {
    private static final String LOG_TAG = DemoApplication.class.getSimpleName();
    private Intent initializeAppService;
    private Intent serverStatusService;


    @Override
    public void onCreate() {
        super.onCreate();
        serverStatusService = new Intent();
        serverStatusService.setClass(DemoApplication.this, ServerStatusService.class);
        startService(serverStatusService);
        // 创建Intent
        initializeAppService = new Intent();
        // 设置Class属性
        initializeAppService.setClass(DemoApplication.this, InitializeAppService.class);
        startService(initializeAppService);
        RoboGuice. setBaseApplicationInjector(this, RoboGuice.DEFAULT_STAGE, RoboGuice. newDefaultRoboModule(this), new ServicesModule(this));

    }

    @Override
    public void onTerminate() {
        stopService(initializeAppService);
        stopService(serverStatusService);
        super.onTerminate();

    }
}
