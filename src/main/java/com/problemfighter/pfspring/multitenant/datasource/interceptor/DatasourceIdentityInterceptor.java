package com.problemfighter.pfspring.multitenant.datasource.interceptor;

import com.problemfighter.pfspring.multitenant.datasource.holder.DatabaseIdentifierHolder;
import com.problemfighter.pfspring.multitenant.datasource.processor.DatasourceIdentityProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;


@Component
public class DatasourceIdentityInterceptor implements WebRequestInterceptor {

    @Autowired
    private DatasourceIdentityProcessor datasourceIdentityProcessor;

    @Override
    public void preHandle(WebRequest request) throws Exception {
        DatabaseIdentifierHolder.setTenantId(datasourceIdentityProcessor.getDatabaseKey(request));
    }

    @Override
    public void postHandle(WebRequest request, ModelMap model) throws Exception {

    }

    @Override
    public void afterCompletion(WebRequest request, Exception ex) throws Exception {
        DatabaseIdentifierHolder.clear();
    }
}
