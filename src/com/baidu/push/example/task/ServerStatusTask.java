package com.baidu.push.example.task;

import android.content.Context;
import com.baidu.push.example.R;
import com.baidu.push.example.event.ServerStatusEvent;
import com.google.inject.Inject;
import de.greenrobot.event.EventBus;
import org.springframework.web.client.RestTemplate;
import roboguice.inject.InjectResource;
import roboguice.util.Ln;
import roboguice.util.RoboAsyncTask;


/**
 * 项目名称：
 * 功能描述：
 * 创建人:ahtt_nsj
 * 创建时间:2014/7/16 0016 10:23
 * 修改人：
 * 修改时间:
 *
 * @版本：V
 */

public class ServerStatusTask extends RoboAsyncTask<Boolean> {

    @Inject
    private RestTemplate restTemplate;

    @InjectResource(R.string.baseAppUrl)
    protected String initializeAppUrl;

    @Inject
    public ServerStatusTask(Context context) {
        super(context);
    }

    @Override
    public Boolean call() throws Exception {
        Boolean status = restTemplate.getForObject(initializeAppUrl + "/server/status", Boolean.class);
        return status;
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onSuccess(Boolean result) {
        EventBus.getDefault().post(new ServerStatusEvent(true));
    }

    @Override
    protected void onException(Exception e) {
        // do this in the UI thread if call() threw an exception
        Ln.d("Interrupting background task %s", this);
        EventBus.getDefault().post(new ServerStatusEvent(false));
    }

    @Override
    protected void onFinally() {
        // always do this in the UI thread after calling call()
        Ln.d("InitializeAppTask onFinally");

    }

    @Override
    protected void onThrowable(Throwable t) throws RuntimeException {
        Ln.d("InitializeAppTask onThrowable");
        EventBus.getDefault().post(new ServerStatusEvent(false));
        super.onThrowable(t);
    }

    @Override
    protected void onInterrupted(Exception e) {
        Ln.d("InitializeAppTask onInterrupted");
        EventBus.getDefault().post(new ServerStatusEvent(false));
        super.onInterrupted(e);
    }
}
