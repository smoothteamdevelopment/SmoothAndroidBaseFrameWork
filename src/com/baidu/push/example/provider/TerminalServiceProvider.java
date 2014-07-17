package com.baidu.push.example.provider;

import android.app.Application;
import com.baidu.push.example.repository.ITerminalRepository;
import com.baidu.push.example.service.impl.TerminalServiceImpl;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * 项目名称：
 * 功能描述：
 * 创建人:ahtt_nsj
 * 创建时间:2014/7/17 0017 9:51
 * 修改人：
 * 修改时间:
 *
 * @版本：V
 */

public class TerminalServiceProvider implements Provider<TerminalServiceImpl> {
    @Inject
    private ITerminalRepository rep;

    @Override
    public TerminalServiceImpl get() {
        return new TerminalServiceImpl(rep);
    }
}
