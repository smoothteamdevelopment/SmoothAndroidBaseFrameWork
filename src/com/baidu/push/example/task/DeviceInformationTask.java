package com.baidu.push.example.task;

import android.content.Context;
import com.baidu.push.example.R;
import com.baidu.push.example.bean.Terminal;
import com.baidu.push.example.event.RegisterDeviceEvent;
import com.baidu.push.example.repository.ITerminalRepository;

import com.google.inject.Inject;
import de.greenrobot.event.EventBus;
import org.springframework.web.client.RestTemplate;
import roboguice.inject.InjectResource;
import roboguice.util.RoboAsyncTask;


/**
 * 项目名称：
 * 功能描述：
 * 创建人:ahtt_nsj
 * 创建时间:2014/6/26 0026 16:07
 * 修改人：
 * 修改时间:
 *
 * @版本：V
 */

public class DeviceInformationTask extends RoboAsyncTask<Terminal> {

    @InjectResource(R.string.updateDeviceInformationTaskUrl)
    public String initializeUrl;
    @Inject
    private ITerminalRepository terminalRepository;
    @Inject
    private RestTemplate restTemplate;
    @Inject
    public DeviceInformationTask(Context context) {
        super(context);
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    public Terminal call() throws Exception {
        Terminal terminalRead = terminalRepository.QueryTerminalModel();
        Terminal terminal = restTemplate.postForObject(initializeUrl + terminalRead.getImei(), terminalRead, Terminal.class);
        return terminal;
    }

    @Override
    protected void onSuccess(Terminal result) {
        // do this in the UI thread if call() succeeds
        if (result != null && result.getId() != null) {
            EventBus.getDefault().post(new RegisterDeviceEvent(true));
            terminalRepository.updateTerminalSharedPreferences(result);
        }

    }

    @Override
    protected void onException(Exception e) throws RuntimeException {
        EventBus.getDefault().post(new RegisterDeviceEvent(false));
        super.onException(e);
    }
}
