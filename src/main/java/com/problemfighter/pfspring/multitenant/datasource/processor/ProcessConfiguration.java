package com.problemfighter.pfspring.multitenant.datasource.processor;

import com.problemfighter.pfspring.multitenant.datasource.config.DefaultDatabaseConfig;
import com.problemfighter.pfspring.multitenant.datasource.config.MultiDatabaseConfig;
import com.problemfighter.pfspring.multitenant.datasource.data.DatasourceProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class ProcessConfiguration {

    @Autowired
    private DefaultDatabaseConfig defaultDatabaseConfig;

    @Autowired
    private MultiDatabaseConfig multiDatabaseConfig;

    private DataSource createDatasource(DatasourceProperty datasourceProperty) {
        if (datasourceProperty.url == null || datasourceProperty.username == null || datasourceProperty.password == null) {
            return null;
        }
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(datasourceProperty.url);
        dataSourceBuilder.url(datasourceProperty.username);
        dataSourceBuilder.url(datasourceProperty.password);

        if (datasourceProperty.driverClassName != null) {
            dataSourceBuilder.driverClassName(datasourceProperty.driverClassName);
        }

        return dataSourceBuilder.build();
    }

    public Boolean isMultiDatasourceEnable() {
        return multiDatabaseConfig.enable;
    }

    public DataSource getDefaultDatasource() {
        return createDatasource(defaultDatabaseConfig.getDatasource());
    }

}
