package com.baidu.push.example.service.impl;

import com.baidu.push.example.bean.module.TerminalModel;
import com.baidu.push.example.repository.ITerminalRepository;
import com.baidu.push.example.service.ITerminalService;
import com.google.inject.Inject;

/**
 * 项目名称：
 * 功能描述：
 * 创建人:ahtt_nsj
 * 创建时间:2014/7/16 0016 17:36
 * 修改人：
 * 修改时间:
 *
 * @版本：V
 */

public class TerminalServiceImpl implements ITerminalService {

    private ITerminalRepository rep;

    public TerminalServiceImpl(ITerminalRepository rep) {
        this.rep = rep;
    }

    @Override
    public TerminalModel QueryTerminalModel() {
        return rep.QueryTerminalModel();
    }

    @Override
    public void updateTerminalSharedPreferences(TerminalModel terminal) {
        this.rep.updateTerminalSharedPreferences(terminal);
    }
}
