package com.baidu.push.example.repository.impl;

import android.content.SharedPreferences;
import android.telephony.TelephonyManager;
import com.baidu.push.example.bean.Terminal;
import com.baidu.push.example.bean.module.TerminalModel;
import com.baidu.push.example.repository.HttpMessageConverterConfig;
import com.baidu.push.example.repository.ITerminalRepository;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.util.Map;

/**
 * 项目名称：
 * 功能描述：
 * 创建人:ahtt_nsj
 * 创建时间:2014/7/16 0016 17:28
 * 修改人：
 * 修改时间:
 *
 * @版本：V
 */

public class ITerminalRepositoryImpl implements ITerminalRepository {
    @Inject
    private TelephonyManager telephonyManager;
    @Inject
    @Named("mobileInfoSharedPreferencesProvider")
    private SharedPreferences sharedPreferences;
    @Inject
    private HttpMessageConverterConfig converterConfig;

    public ITerminalRepositoryImpl() {
    }

    @Override
    public TerminalModel QueryTerminalModel() {
        TerminalModel terminalModel = new TerminalModel();
        terminalModel.setImei(telephonyManager.getDeviceId());
        terminalModel.setModel(android.os.Build.MODEL);
        terminalModel.setNumber(telephonyManager.getLine1Number());
        String subscriberId = telephonyManager.getSubscriberId();
        if (subscriberId.startsWith("46000") || subscriberId.startsWith("46002")) {
            terminalModel.setType("中国移动");
        } else if (subscriberId.startsWith("46001")) {
            terminalModel.setType("中国联通");
        } else if (subscriberId.startsWith("46003")) {
            terminalModel.setType("中国电信");
        }
        terminalModel.setActory(android.os.Build.MODEL);
        terminalModel.setSim(telephonyManager.getSimSerialNumber());
        return terminalModel;
    }


    @Override
    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public HttpMessageConverterConfig getConverterConfig() {
        return converterConfig;
    }

    public TelephonyManager getTelephonyManager() {
        return telephonyManager;
    }

    @Override
    public void updateTerminalSharedPreferences(Terminal terminal) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Map<String, String> objectAsMap = converterConfig.getMapper().convertValue(terminal, Map.class);
        for (Map.Entry<String, String> entry : objectAsMap.entrySet()) {
            editor.putString(entry.getKey(), entry.getValue());
        }
        editor.commit();
    }

}
