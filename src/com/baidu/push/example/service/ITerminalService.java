package com.baidu.push.example.service;

import com.baidu.push.example.bean.Terminal;
import com.baidu.push.example.bean.module.TerminalModel;

/**
 * 项目名称：
 * 功能描述：
 * 创建人:ahtt_nsj
 * 创建时间:2014/7/16 0016 17:35
 * 修改人：
 * 修改时间:
 *
 * @版本：V
 */

public interface ITerminalService {
    public TerminalModel QueryTerminalModel();

    public void updateTerminalSharedPreferences(TerminalModel terminal);
}
