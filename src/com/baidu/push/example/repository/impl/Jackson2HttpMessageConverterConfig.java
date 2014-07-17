package com.baidu.push.example.repository.impl;

import com.baidu.push.example.repository.HttpMessageConverterConfig;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * 项目名称：
 * 功能描述：
 * 创建人:ahtt_nsj
 * 创建时间:2014/7/17 0017 16:09
 * 修改人：
 * 修改时间:
 *
 * @版本：V
 */

public class Jackson2HttpMessageConverterConfig implements HttpMessageConverterConfig {
    private ObjectMapper mapper;

    private MappingJackson2HttpMessageConverter converter;

    public Jackson2HttpMessageConverterConfig() {
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(MediaType.parseMediaTypes("application/hal+json"));
        converter.setSupportedMediaTypes(MediaType.parseMediaTypes("application/json"));
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        converter.setObjectMapper(mapper);
    }

    @Override
    public ObjectMapper getMapper() {
        return mapper;
    }

    @Override
    public HttpMessageConverter getConverter() {
        return converter;
    }
}
