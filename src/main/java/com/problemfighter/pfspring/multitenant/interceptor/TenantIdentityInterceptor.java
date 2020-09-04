package com.problemfighter.pfspring.multitenant.interceptor;

import com.problemfighter.pfspring.common.common.Console;
import com.problemfighter.pfspring.multitenant.holder.TenantIdentifierHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;


@Component
public class TenantIdentityInterceptor implements WebRequestInterceptor {


    @Override
    public void preHandle(WebRequest request) throws Exception {
        String tenant = request.getHeader("tenant");
        Console.log(((ServletWebRequest)request).getRequest().getRequestURI());
        if (tenant == null){
            tenant = "vw";
        }
        Console.log("-------- TenantIdentityInterceptor");
        TenantIdentifierHolder.setTenantId(tenant);
    }

    @Override
    public void postHandle(WebRequest request, ModelMap model) throws Exception {

    }

    @Override
    public void afterCompletion(WebRequest request, Exception ex) throws Exception {
        TenantIdentifierHolder.clear();
    }
}
