package com.baidu.push.example;

import android.content.Intent;


import com.baidu.frontia.FrontiaApplication;
import com.baidu.push.example.service.InitializeAppService;


/*
 * 如果您的工程中实现了Application的继承类，那么，您需要将父类改为com.baidu.frontia.FrontiaApplication。
 * 如果您没有实现Application的继承类，那么，请在AndroidManifest.xml的Application标签中增加属性： 
 * <application android:name="com.baidu.frontia.FrontiaApplication"
 * 。。。
 */
public class DemoApplication extends FrontiaApplication {
    private static final String LOG_TAG = DemoApplication.class.getSimpleName();
    private   Intent intent;


    @Override
    public void onCreate() {
        super.onCreate();
        // 创建Intent
         intent = new Intent();
        // 设置Class属性
        intent.setClass(DemoApplication.this, InitializeAppService.class);
        startService(intent);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        stopService(intent);
    }
}
