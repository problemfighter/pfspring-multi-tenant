package com.problemfighter.pfspring.multitenant.datasource.interceptor;

import com.problemfighter.pfspring.common.common.Console;
import com.problemfighter.pfspring.multitenant.datasource.config.DefaultDatabaseConfig;
import com.problemfighter.pfspring.multitenant.datasource.config.MultiDatabaseConfig;
import com.problemfighter.pfspring.multitenant.datasource.config.UrlMapToDatabase;
import com.problemfighter.pfspring.multitenant.datasource.holder.DatabaseIdentifierHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;


@Component
public class TenantIdentityInterceptor implements WebRequestInterceptor {

    @Autowired
    private UrlMapToDatabase urlMapToDatabase;

    @Autowired
    private DefaultDatabaseConfig defaultDatabaseConfig;

    @Autowired
    private MultiDatabaseConfig multiDatabaseConfig;

    @Override
    public void preHandle(WebRequest request) throws Exception {
        String tenant = request.getHeader("tenant");
        Console.log(((ServletWebRequest) request).getRequest().getRequestURI());
        if (tenant == null) {
            tenant = "vw";
        }
        Console.log("-------- TenantIdentityInterceptor");
        DatabaseIdentifierHolder.setTenantId(tenant);
    }

    @Override
    public void postHandle(WebRequest request, ModelMap model) throws Exception {

    }

    @Override
    public void afterCompletion(WebRequest request, Exception ex) throws Exception {
        DatabaseIdentifierHolder.clear();
    }
}
