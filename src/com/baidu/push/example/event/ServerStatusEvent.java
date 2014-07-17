package com.baidu.push.example.event;

/**
 * 项目名称：
 * 功能描述：
 * 创建人:ahtt_nsj
 * 创建时间:2014/7/16 0016 10:38
 * 修改人：
 * 修改时间:
 *
 * @版本：V
 */

public class ServerStatusEvent {

    private Boolean status;

    public ServerStatusEvent(Boolean status) {
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }
}
