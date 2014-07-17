package com.baidu.push.example;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.*;

import com.baidu.push.example.bean.module.TerminalModel;
import com.baidu.push.example.event.*;

import com.baidu.push.example.service.ITerminalService;
import com.baidu.push.example.service.InitializeAppService;

//import de.greenrobot.event.EventBus;
import roboguice.activity.RoboActivity;
import roboguice.event.EventManager;
import roboguice.event.Observes;
import roboguice.inject.*;
import com.google.inject.Inject;


/**
 * 项目名称：
 * 功能描述：
 * 创建人:ahtt_nsj
 * 创建时间:2014/6/19 0019 14:54
 * 修改人：
 * 修改时间:
 *
 * @版本：V
 */
@ContentView(R.layout.activity_welcome)
public class WelcomeActivity extends RoboActivity {
    @Inject
    protected EventManager eventManager;
    @Inject
    protected InitializeAppService initializeAppService;
    @Inject
    protected ITerminalService terminalService;

    @InjectView(R.id.image_btn_login_confirm)
    ImageButton loginBtn = null;
    @InjectView(R.id.usernameeditText)
    TextView loginName = null;
    @InjectView(R.id.userpasseditText)
    TextView loginPass = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBtn.setOnClickListener(LoginViewListener);
        /**
         * 软件启动执行系统初始化操作
         */
        Intent serviceIntent = new Intent(this, InitializeAppService.class);
        bindService(serviceIntent, initializeAppService, Context.BIND_AUTO_CREATE);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(initializeAppService);
    }

    private View.OnClickListener LoginViewListener = new View.OnClickListener() {
        public void onClick(View v) {
            eventManager.fire(new UserLoginEvent(loginName.getText().toString(), loginPass.getText().toString()));
        }
    };

    protected void handleEvent(@Observes UserLoginEvent event) {
        TerminalModel terminalModel = terminalService.QueryTerminalModel();
        Toast.makeText(this, "UserLogin Event" + terminalModel.getImei()+"\t"+terminalModel.getModel(),
                Toast.LENGTH_LONG).show();
    }

    //提供注入点
//    public void setiTimeService(ITimeService iTimeService) {
//        this.iTimeService = iTimeService;
//    }
}
