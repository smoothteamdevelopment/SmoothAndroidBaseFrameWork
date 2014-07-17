package com.baidu.push.example.task;

import android.content.Context;
import android.content.SharedPreferences;
import com.baidu.push.example.R;
import com.baidu.push.example.bean.App;
import com.baidu.push.example.event.InitializeAppEvent;
import com.baidu.push.example.repository.HttpMessageConverterConfig;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import de.greenrobot.event.EventBus;
import org.springframework.web.client.RestTemplate;
import roboguice.inject.InjectResource;
import roboguice.util.Ln;
import roboguice.util.RoboAsyncTask;

import java.util.Map;

/**
 * 项目名称：
 * 功能描述：
 * 创建人:ahtt_nsj
 * 创建时间:2014/6/26 0026 10:35
 * 修改人：
 * 修改时间:
 *
 * @版本：V
 */

public class InitializeAppTask extends RoboAsyncTask<App> {
    @Inject
    @Named("mobileInfoSharedPreferencesProvider")
    private SharedPreferences sharedPreferences;
    @InjectResource(R.string.initializeAppUrl)
    protected String initializeAppUrl;
    @InjectResource(R.string.baiduid)
    protected String baiduid;
    @Inject
    private RestTemplate restTemplate;
    @Inject
    private HttpMessageConverterConfig httpMessageConverterConfig;

    @Inject
    public InitializeAppTask(Context context) {
        super(context);
    }

    @Override
    public App call() throws Exception {
        App app = restTemplate.getForObject(initializeAppUrl + baiduid, App.class);
        app.setBaiduid(baiduid);
        return app;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onSuccess(App result) {
        // do this in the UI thread if call() succeeds
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Map<String, String> objectAsMap = httpMessageConverterConfig.getMapper().convertValue(result, Map.class);
        for (Map.Entry<String, String> entry : objectAsMap.entrySet()) {
            editor.putString(entry.getKey(), entry.getValue());
        }
        editor.commit();
        EventBus.getDefault().post(new InitializeAppEvent(result.getSecretkey(), result.getApikey(), true));
    }

    @Override
    protected void onException(Exception e) {
        // do this in the UI thread if call() threw an exception
        Ln.d("Interrupting background task %s", this);
        EventBus.getDefault().post(new InitializeAppEvent(null, null, false));
    }

    @Override
    protected void onFinally() {
        // always do this in the UI thread after calling call()
        Ln.d("InitializeAppTask onFinally");

    }

    @Override
    protected void onThrowable(Throwable t) throws RuntimeException {
        Ln.d("InitializeAppTask onThrowable");
        super.onThrowable(t);
    }

    @Override
    protected void onInterrupted(Exception e) {
        Ln.d("InitializeAppTask onInterrupted");
        super.onInterrupted(e);
    }
}
