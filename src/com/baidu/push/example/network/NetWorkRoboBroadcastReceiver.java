package com.baidu.push.example.network;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;
import com.baidu.push.example.event.NetworkStateEvent;
import com.baidu.push.example.listener.NetworkListener;
import com.baidu.push.example.utils.NetworkUtil;
import com.google.inject.Inject;
import de.greenrobot.event.EventBus;
import roboguice.event.EventManager;
import roboguice.receiver.RoboBroadcastReceiver;
import roboguice.util.Ln;

/**
 * 项目名称：
 * 功能描述：
 * 创建人:ahtt_nsj
 * 创建时间:2014/7/11 0011 15:07
 * 修改人：
 * 修改时间:
 *
 * @版本：V
 */

public class NetWorkRoboBroadcastReceiver extends RoboBroadcastReceiver {

    @Override
    protected void handleReceive(Context context, Intent intent) {
        Ln.i("onReceive Action: " + intent.getAction());
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            NetworkInfo info = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
            String typeName = info.getTypeName();
            String subtypeName = info.getSubtypeName();
            boolean available = info.isAvailable();
            String status = NetworkUtil.getConnectivityStatusString(context);
            EventBus.getDefault().post(new NetworkStateEvent(typeName, subtypeName, available));
            Toast.makeText(context, status, Toast.LENGTH_LONG).show();
        }
    }
}
