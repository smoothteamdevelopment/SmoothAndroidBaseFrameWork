package com.baidu.push.example.repository;

import com.baidu.push.example.provider.HttpMessageConverterConfigProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.ProvidedBy;
import org.springframework.http.converter.HttpMessageConverter;

/**
 * 项目名称：
 * 功能描述：
 * 创建人:ahtt_nsj
 * 创建时间:2014/7/17 0017 16:03
 * 修改人：
 * 修改时间:
 *
 * @版本：V
 */
@ProvidedBy(HttpMessageConverterConfigProvider.class)
public interface HttpMessageConverterConfig {

    public ObjectMapper getMapper();

    public HttpMessageConverter getConverter();

}
