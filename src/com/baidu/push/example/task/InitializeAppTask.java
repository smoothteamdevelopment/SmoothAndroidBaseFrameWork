package com.baidu.push.example.task;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.baidu.push.example.R;
import com.baidu.push.example.bean.App;
import com.baidu.push.example.bean.Jackson2HttpMessageConverterConfig;
import com.baidu.push.example.event.InitializeAppEvent;
import com.google.inject.Inject;
//import de.greenrobot.event.EventBus;
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
    private SharedPreferences perferences;
    private Jackson2HttpMessageConverterConfig converterConfig;
    @InjectResource(R.string.initializeAppUrl)
    protected String initializeAppUrl;
    @InjectResource(R.string.baiduid)
    protected String baiduid;

    @Inject
    public InitializeAppTask(Context context) {
        super(context);
    }

    @Override
    public App call() throws Exception {
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(converterConfig.getConverter());
            App app = restTemplate.getForObject(initializeAppUrl + baiduid, App.class);
            app.setBaiduid(baiduid);
            return app;
        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage(), e);
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        converterConfig = new Jackson2HttpMessageConverterConfig();
        perferences = context.getSharedPreferences("com.baidu.push", context.MODE_PRIVATE);
    }

    @Override
    protected void onSuccess(App result) {
        // do this in the UI thread if call() succeeds
        SharedPreferences.Editor editor = perferences.edit();
        Map<String, String> objectAsMap = converterConfig.getMapper().convertValue(result, Map.class);
        for (Map.Entry<String, String> entry : objectAsMap.entrySet()) {
            editor.putString(entry.getKey(), entry.getValue());
        }
        editor.commit();
        EventBus.getDefault().post(new InitializeAppEvent(result.getSecretkey(),result.getApikey(),true));
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

}
