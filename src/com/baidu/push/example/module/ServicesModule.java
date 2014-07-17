package com.baidu.push.example.module;

import android.app.Application;
import android.content.SharedPreferences;
import com.baidu.push.example.provider.RestTemplateConfigProvider;
import com.baidu.push.example.provider.TerminalServiceProvider;
import com.baidu.push.example.repository.ITerminalRepository;
import com.baidu.push.example.repository.impl.ITerminalRepositoryImpl;
import com.baidu.push.example.service.ITerminalService;
import com.baidu.push.example.service.impl.TerminalServiceImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

/**
 * 项目名称：
 * 功能描述：
 * 创建人:ahtt_nsj
 * 创建时间:2014/7/16 0016 15:11
 * 修改人：
 * 修改时间:
 *
 * @版本：V
 */

public class ServicesModule extends AbstractModule {

    protected Application application;

    public ServicesModule(Application application) {
        this.application = application;
    }

    public final String mobileInfoSharedPreferencesName = "com.baidu.push.mobileInfo";
    public final String baiduPushSharedPreferencesName = "com.baidu.push";

    @Override
    protected void configure() {
        this.bind(ITerminalRepository.class).to(ITerminalRepositoryImpl.class);
        this.bind(ITerminalService.class).to(TerminalServiceImpl.class);
        this.bind(TerminalServiceImpl.class).toProvider(TerminalServiceProvider.class);
        this.bind(RestTemplate.class).toProvider(RestTemplateConfigProvider.class);
    }

    @Provides
    @Named("mobileInfoSharedPreferencesProvider")
    SharedPreferences mobileInfoSharedPreferencesName() {
        return application.getSharedPreferences(mobileInfoSharedPreferencesName, application.MODE_PRIVATE);
    }

    @Provides
    @Named("baiduPushSharedPreferencesName")
    SharedPreferences baiduPushSharedPreferencesName() {
        return application.getSharedPreferences(baiduPushSharedPreferencesName, application.MODE_PRIVATE);
    }
    @Provides
    @Named("stringHttpMessageConverter")
    HttpMessageConverter StringHttpMessageConverter() {
        return new StringHttpMessageConverter(Charset.defaultCharset()) ;
    }



}
