package com.problemfighter.pfspring.multitenant.datasource;

import com.problemfighter.pfspring.multitenant.datasource.processor.MultiDatasourceRouter;
import com.problemfighter.pfspring.multitenant.datasource.processor.ProcessConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    @Autowired
    private ProcessConfiguration processConfiguration;

    @Bean
    public DataSource dataSource() {
        if (processConfiguration.isMultiDatasourceEnable()) {
            MultiDatasourceRouter multiDatasourceRouter = new MultiDatasourceRouter();
            return multiDatasourceRouter;
        } else {
            return processConfiguration.getDefaultDatasource();
        }
    }
}
