package com.baidu.push.example.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import com.baidu.push.example.event.NetworkStateEvent;
import com.baidu.push.example.listener.BaiduPushListener;
import com.baidu.push.example.listener.InitializeAppListener;
import com.baidu.push.example.listener.NetworkListener;
import com.baidu.push.example.task.InitializeAppTask;
import com.baidu.push.example.utils.NetworkUtil;
import com.google.inject.Inject;
import de.greenrobot.event.EventBus;
import roboguice.service.RoboService;
import roboguice.util.Ln;

/**
 * 项目名称：
 * 功能描述：
 * 创建人:ahtt_nsj
 * 创建时间:2014/7/7 0007 14:26
 * 修改人：
 * 修改时间:
 *
 * @版本：V
 */

public class InitializeAppService extends RoboService implements ServiceConnection {

    @Inject
    private EventBus eventBus;
    @Inject
    InitializeAppTask initializeAppTask;

    @Override
    public void onCreate() {

        super.onCreate();
        InitializeAppListener initializeAppListener = new InitializeAppListener(this.getApplicationContext());
        BaiduPushListener baiduPushListener = new BaiduPushListener(this.getApplicationContext());
        //All injections are available from here :
        if (!NetworkUtil.checkInternetConnection(this.getApplicationContext())) {
            Ln.d("当前无可能网络");
            /**
             * 系统当前无网络，创建监听器监听网络情况，在有网络的情况获取最新系统参数。
             */
            EventBus.getDefault().register(initializeAppListener);
        } else {
            Ln.d("当前网络 %s", NetworkUtil.getConnectivityStatusString(this.getApplicationContext()));
            /**
             * 获取最新系统参数
             */
            initializeAppTask.execute();
        }
        EventBus.getDefault().register(baiduPushListener);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        binder = new InitializeAppBinder();
        return binder;
    }

    private Binder binder;

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        try {
            Ln.d("onServiceConnected" + name.toShortString() + service.getInterfaceDescriptor());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

        Ln.d("onServiceDisconnected" + name.toShortString());

    }

    public class InitializeAppBinder extends Binder {

        /**
         * Returns the bound service.
         */
        public InitializeAppService getService() {
            return InitializeAppService.this;
        }
    }
}
