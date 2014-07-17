package com.baidu.push.example.event;

/**
 * 项目名称：
 * 功能描述：
 * 创建人:ahtt_nsj
 * 创建时间:2014/7/16 0016 9:14
 * 修改人：
 * 修改时间:
 *
 * @版本：V
 */

public class RegisterDeviceEvent {
    private Boolean hasRegister;

    public RegisterDeviceEvent(Boolean hasRegister) {
        this.hasRegister = hasRegister;
    }

    public RegisterDeviceEvent() {
        this.hasRegister = false;
    }

    public Boolean getHasRegister() {
        return hasRegister;
    }
}
