package com.baidu.push.example.listener;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.baidu.push.example.event.NetworkStateEvent;
import com.baidu.push.example.event.RegisterDeviceEvent;
import com.baidu.push.example.event.ServerStatusEvent;
import com.baidu.push.example.task.DeviceInformationTask;
import de.greenrobot.event.EventBus;
import roboguice.util.Ln;

/**
 * 项目名称：
 * 功能描述：
 * 创建人:ahtt_nsj
 * 创建时间:2014/7/16 0016 9:12
 * 修改人：
 * 修改时间:
 *
 * @版本：V
 */

public class RegisterDeviceListener {
    private Context applicationContext;
    public RegisterDeviceListener(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * 监听获取最新系统参数InitializeAppEvent事件，如果成功，执行初始化PushManager。
     */
    public void onEventBackgroundThread(RegisterDeviceEvent event) {
        Ln.d("RegisterDeviceListener onEventBackgroundThread RegisterDeviceEvent HasRegister:  %s", event.getHasRegister());
        if (event.getHasRegister()) {
            EventBus.getDefault().unregister(this);
        } else {
            DeviceInformationTask deviceInformationTask = new DeviceInformationTask(this.applicationContext);
            deviceInformationTask.execute();
        }
    }

    /**
     * 系统当前无网络，监听ServerStatusEvent，在有网络的情况执行获取最新系统参数任务。
     */
    public void onEventBackgroundThread(ServerStatusEvent event) {
        Ln.d("RegisterDeviceListener onEventBackgroundThread ServerStatusEventServerStatus:  %s", event.getStatus());
        if (event.getStatus()) {
            EventBus.getDefault().post(new RegisterDeviceEvent(false));
        }
    }



}
