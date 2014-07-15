package com.baidu.push.example.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;
import com.baidu.push.example.event.NetworkStateEvent;
import com.baidu.push.example.utils.NetworkUtil;
import com.google.inject.Inject;
import roboguice.event.EventManager;
import roboguice.util.Ln;
//import de.greenrobot.event.EventBus;

/**
 * 项目名称：
 * 功能描述：
 * 创建人:ahtt_nsj
 * 创建时间:2014/6/28 0028 10:47
 * 修改人：
 * 修改时间:
 *
 * @版本：V
 */

public class NetworkStateReceiver extends BroadcastReceiver {
    @Inject
    protected EventManager eventManager;
    @Override
    public void onReceive(Context ctx, Intent intent) {
        Ln.i( "onReceive Action: " + intent.getAction());
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            NetworkInfo info = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
            String typeName = info.getTypeName();
            String subtypeName = info.getSubtypeName();
            boolean available = info.isAvailable();
            String status = NetworkUtil.getConnectivityStatusString(ctx);
//            EventBus.getDefault().post(new NetworkStateEvent(typeName, subtypeName, available));
            eventManager.fire(new NetworkStateEvent(typeName, subtypeName, available));
            Toast.makeText(ctx, status, Toast.LENGTH_LONG).show();

        }
    }

    private static final String LOG_TAG = NetworkStateReceiver.class.getSimpleName();
}
