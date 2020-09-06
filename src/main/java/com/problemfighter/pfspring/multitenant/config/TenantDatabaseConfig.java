package com.problemfighter.pfspring.multitenant.config;

import com.problemfighter.pfspring.multitenant.data.DatasourceProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Data
@Configuration
@ConfigurationProperties(prefix = "tenants")
public class TenantDatabaseConfig {

    public LinkedHashMap<String, DatasourceProperty> datasources;

}
