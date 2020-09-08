package com.problemfighter.pfspring.multitenant.datasource.config;

import com.problemfighter.pfspring.multitenant.datasource.data.DatasourceProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Configuration
@ConfigurationProperties(prefix = "multidatasource")
public class MultiDatabaseConfig {

    public LinkedHashMap<String, DatasourceProperty> datasources;
    public Boolean enable = false;
    public String dbIdentifierKey = "identity";

    public LinkedHashMap<String, DatasourceProperty> getDatasources() {
        return datasources;
    }

    public void setDatasources(LinkedHashMap<String, DatasourceProperty> datasources) {
        this.datasources = datasources;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getDbIdentifierKey() {
        return dbIdentifierKey;
    }

    public void setDbIdentifierKey(String dbIdentifierKey) {
        this.dbIdentifierKey = dbIdentifierKey;
    }
}
