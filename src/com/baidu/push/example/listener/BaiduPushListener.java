package com.baidu.push.example.listener;

import android.content.Context;
import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.baidu.push.example.event.InitializeAppEvent;
import com.baidu.push.example.event.NetworkStateEvent;
import com.baidu.push.example.task.InitializeAppTask;
import de.greenrobot.event.EventBus;
import roboguice.util.Ln;

/**
 * 项目名称：
 * 功能描述：
 * 创建人:ahtt_nsj
 * 创建时间:2014/7/15 0015 14:54
 * 修改人：
 * 修改时间:
 *
 * @版本：V
 */

public class BaiduPushListener {
    private Context applicationContext;
    private Boolean initialize;

    public BaiduPushListener(Context applicationContext) {
        this.applicationContext = applicationContext;
        initialize = false;
    }

    /**
     * 监听获取最新系统参数InitializeAppEvent事件，如果成功，执行初始化PushManager。
     */
    public void onEventBackgroundThread(InitializeAppEvent event) {
        Ln.d("BaiduPushListener onEventBackgroundThread InitializeAppEvent Apikey:  %s, Secretkey: %s, Initialize: %s", event.getApikey(), event.getSecretkey(), event.getInitialize());
        this.initialize = event.getInitialize();
        if (this.initialize) {
            PushManager.startWork(applicationContext,
                    PushConstants.LOGIN_TYPE_API_KEY, event.getApikey());
            /**
             * 不在执行PushManager初始化工作。
             */
            EventBus.getDefault().unregister(this);
        }

    }

}
