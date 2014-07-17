package com.baidu.push.example.listener;

import android.content.Context;
import com.baidu.push.example.event.InitializeAppEvent;
import com.baidu.push.example.event.NetworkStateEvent;
import com.baidu.push.example.event.ServerStatusEvent;
import com.baidu.push.example.event.UserLoginEvent;
import com.baidu.push.example.task.InitializeAppTask;
import de.greenrobot.event.EventBus;
import roboguice.event.EventListener;
import roboguice.event.Observes;
import roboguice.util.Ln;

/**
 * 项目名称：
 * 功能描述：
 * 创建人:ahtt_nsj
 * 创建时间:2014/7/2 0002 16:33
 * 修改人：
 * 修改时间:
 *
 * @版本：V
 */

public class InitializeAppListener {
    private Context applicationContext;
    private Boolean initialize;

    public InitializeAppListener(Context applicationContext) {
        this.applicationContext = applicationContext;
        initialize = false;
    }

    /**
     * 系统当前无网络，监听NetworkStateEvent，在有网络的情况执行获取最新系统参数任务。
     */
    public void onEventBackgroundThread(ServerStatusEvent event) {
        Ln.d("InitializeAppListener onEventBackgroundThread ServerStatusEvent  ServerStatus:  %s", event.getStatus());
        if (this.initialize.equals(Boolean.FALSE) && event.getStatus()) {
            InitializeAppTask initializeAppTask = new InitializeAppTask(this.applicationContext);
            initializeAppTask.execute();
        }
    }

    /**
     * 系统当前无网络，监听NetworkStateEvent，在有网络的情况执行获取最新系统参数任务。
     */
    public void onEventBackgroundThread(InitializeAppEvent event) {
        Ln.d("NetworkListener onEventBackgroundThread InitializeAppEvent initialize:  %s", event.getInitialize());
        this.initialize = event.getInitialize();
        if (this.initialize) {
            /**
             * 不在监听系统初始化所有监听工作。
             */
            EventBus.getDefault().unregister(this);
        }
    }

}
