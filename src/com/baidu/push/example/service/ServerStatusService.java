package com.baidu.push.example.service;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import com.baidu.push.example.R;
import com.baidu.push.example.task.ServerStatusTask;
import com.google.inject.Inject;
import de.greenrobot.event.EventBus;
import roboguice.inject.InjectResource;
import roboguice.service.RoboService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 项目名称：
 * 功能描述：服务器状态检测服务通过定时器定时执行服务器状态检查任务。
 * 创建人:ahtt_nsj
 * 创建时间:2014/7/16 0016 10:45
 * 修改人：
 * 修改时间:
 *
 * @版本：V
 */

public class ServerStatusService extends RoboService {
    @Inject
    private ServerStatusTask serverStatusTask;
    @InjectResource(R.string.initialDelay)
    private String initialDelay;
    @InjectResource(R.string.delay)
    private String delay;

    @Override
    public void onCreate() {
        super.onCreate();
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay
                (new Runnable() {
                    public void run() {
                        serverStatusTask.execute();
                    }
                }, Long.valueOf(initialDelay), Long.valueOf(delay), TimeUnit.SECONDS);
    }

    @Override
    public IBinder onBind(Intent intent) {
        binder = new ServerStatusServiceBinder();
        return binder;
    }

    private Binder binder;

    public class ServerStatusServiceBinder extends Binder {

        /**
         * Returns the bound service.
         */
        public ServerStatusService getService() {
            return ServerStatusService.this;
        }
    }
}
