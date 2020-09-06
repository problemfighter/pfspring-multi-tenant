package com.problemfighter.pfspring.multitenant.config;


import com.problemfighter.pfspring.multitenant.data.DatasourceProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Data
@Configuration
@ConfigurationProperties(prefix = "spring")
public class DefaultDatabaseConfig {

    public DatasourceProperty datasource;

}
