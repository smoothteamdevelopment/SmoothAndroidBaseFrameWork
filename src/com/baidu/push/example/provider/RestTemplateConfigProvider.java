package com.baidu.push.example.provider;

import com.baidu.push.example.repository.HttpMessageConverterConfig;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * 项目名称：
 * 功能描述：
 * 创建人:ahtt_nsj
 * 创建时间:2014/7/17 0017 16:39
 * 修改人：
 * 修改时间:
 *
 * @版本：V
 */

public class RestTemplateConfigProvider implements Provider<RestTemplate> {
    @Inject
    HttpMessageConverterConfig httpMessageConverterConfig;
    @Inject
    @Named("stringHttpMessageConverter")
    HttpMessageConverter stringHttpMessageConverter;

    @Override
    public RestTemplate get() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(httpMessageConverterConfig.getConverter());
        restTemplate.getMessageConverters().add(stringHttpMessageConverter);
        return restTemplate;
    }
}
