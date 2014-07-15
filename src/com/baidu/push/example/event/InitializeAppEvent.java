package com.baidu.push.example.event;

/**
 * 项目名称：
 * 功能描述：
 * 创建人:ahtt_nsj
 * 创建时间:2014/7/2 0002 16:24
 * 修改人：
 * 修改时间:
 *
 * @版本：V
 */

public class InitializeAppEvent {
    private Boolean initialize;
    private String secretkey;
    private String apikey;

    public InitializeAppEvent(String secretkey, String apikey, Boolean b) {
        this.secretkey = secretkey;
        this.apikey = apikey;
        this.initialize = b;
    }

    public Boolean getInitialize() {
        return initialize;
    }

    public String getSecretkey() {
        return secretkey;
    }

    public String getApikey() {
        return apikey;
    }
}
