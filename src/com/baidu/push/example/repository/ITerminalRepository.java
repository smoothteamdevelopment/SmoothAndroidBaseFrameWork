package com.baidu.push.example.repository;

import android.content.SharedPreferences;
import android.telephony.TelephonyManager;
import com.baidu.push.example.bean.Terminal;
import com.baidu.push.example.bean.module.TerminalModel;
import com.baidu.push.example.repository.impl.Jackson2HttpMessageConverterConfig;

/**
 * 项目名称：
 * 功能描述：
 * 创建人:ahtt_nsj
 * 创建时间:2014/7/16 0016 17:27
 * 修改人：
 * 修改时间:
 *
 * @版本：V
 */

public interface ITerminalRepository {
    public TerminalModel QueryTerminalModel();


    public SharedPreferences getSharedPreferences();

    public HttpMessageConverterConfig getConverterConfig();

    public TelephonyManager getTelephonyManager();

    public void updateTerminalSharedPreferences(Terminal terminal);

}
