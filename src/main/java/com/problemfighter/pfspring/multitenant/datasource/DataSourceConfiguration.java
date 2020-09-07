package com.problemfighter.pfspring.multitenant.datasource;

import com.problemfighter.pfspring.multitenant.datasource.processor.MultiDatasourceRouter;
import com.problemfighter.pfspring.multitenant.datasource.processor.ProcessConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
public class DataSourceConfiguration {

    @Autowired
    private ProcessConfiguration processConfiguration;

    private Map<Object, Object> registeredDataSources;

    @Bean
    public DataSource dataSource() {
        if (processConfiguration.isMultiDatasourceEnable()) {
            MultiDatasourceRouter multiDatasourceRouter = new MultiDatasourceRouter();
            multiDatasourceRouter.setDefaultTargetDataSource(processConfiguration.getDefaultDatasource());
            registeredDataSources = processConfiguration.getAllRegisteredDatasource();
            multiDatasourceRouter.setTargetDataSources(registeredDataSources);
            return multiDatasourceRouter;
        } else {
            return processConfiguration.getDefaultDatasource();
        }
    }

    public void addDataSource(String key, Object source){
        registeredDataSources.put(key, source);
    }
}
