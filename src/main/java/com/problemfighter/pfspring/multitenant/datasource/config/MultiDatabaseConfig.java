package com.problemfighter.pfspring.multitenant.datasource.config;

import com.problemfighter.pfspring.multitenant.datasource.data.DatasourceProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Data
@Configuration
@ConfigurationProperties(prefix = "multidatasource")
public class MultiDatabaseConfig {

    public LinkedHashMap<String, DatasourceProperty> datasources;
    public Boolean enable = false;
    public String dbIdentifierKey = "identity";

}
