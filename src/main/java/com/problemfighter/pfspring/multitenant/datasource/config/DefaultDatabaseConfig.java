package com.problemfighter.pfspring.multitenant.datasource.config;


import com.problemfighter.pfspring.multitenant.datasource.data.DatasourceProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "spring")
public class DefaultDatabaseConfig {

    public DatasourceProperty datasource;

    public DatasourceProperty getDatasource() {
        return datasource;
    }

    public void setDatasource(DatasourceProperty datasource) {
        this.datasource = datasource;
    }
}
