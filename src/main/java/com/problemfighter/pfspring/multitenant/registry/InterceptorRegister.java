package com.problemfighter.pfspring.multitenant.registry;

import com.problemfighter.pfspring.multitenant.interceptor.TenantIdentityInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorRegister implements WebMvcConfigurer {

    private final TenantIdentityInterceptor tenantIdentityInterceptor;

    public InterceptorRegister(TenantIdentityInterceptor tenantIdentityInterceptor) {
        this.tenantIdentityInterceptor = tenantIdentityInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addWebRequestInterceptor(tenantIdentityInterceptor);
    }

}
