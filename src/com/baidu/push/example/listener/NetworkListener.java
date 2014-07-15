package com.baidu.push.example.listener;

import android.util.Log;
import com.baidu.push.example.event.NetworkStateEvent;
import de.greenrobot.event.EventBus;
import roboguice.event.EventListener;
import roboguice.util.Ln;

/**
 * 项目名称：
 * 功能描述：
 * 创建人:ahtt_nsj
 * 创建时间:2014/7/2 0002 16:06
 * 修改人：
 * 修改时间:
 *
 * @版本：V
 */

public class NetworkListener {
    /**
     * 系统全局监听网络状态事件
     *
     * @param event
     */
    public void onEventBackgroundThread(NetworkStateEvent event) {
        Ln.d("NetworkListener onEventBackgroundThread Network Type:  %s, subtype: %s, available: %s", event.getmTypeName(), event.getmSubtypeName(), event.ismAvailable());
    }
}
