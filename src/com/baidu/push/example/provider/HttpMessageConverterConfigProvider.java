package com.baidu.push.example.provider;

import com.baidu.push.example.repository.impl.Jackson2HttpMessageConverterConfig;
import com.google.inject.Provider;

/**
 * 项目名称：
 * 功能描述：
 * 创建人:ahtt_nsj
 * 创建时间:2014/7/17 0017 16:00
 * 修改人：
 * 修改时间:
 *
 * @版本：V
 */

public class HttpMessageConverterConfigProvider implements Provider<Jackson2HttpMessageConverterConfig> {

    @Override
    public Jackson2HttpMessageConverterConfig get() {
        return new Jackson2HttpMessageConverterConfig();
    }

}
