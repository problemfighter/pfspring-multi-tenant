package com.problemfighter.pfspring.multitenant.registry;

import com.problemfighter.pfspring.multitenant.datasource.interceptor.DatasourceIdentityInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorRegister implements WebMvcConfigurer {

    private final DatasourceIdentityInterceptor datasourceIdentityInterceptor;

    public InterceptorRegister(DatasourceIdentityInterceptor datasourceIdentityInterceptor) {
        this.datasourceIdentityInterceptor = datasourceIdentityInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addWebRequestInterceptor(datasourceIdentityInterceptor);
    }

}
