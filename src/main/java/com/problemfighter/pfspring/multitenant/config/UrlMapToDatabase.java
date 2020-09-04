package com.problemfighter.pfspring.multitenant.config;


import com.problemfighter.pfspring.common.factory.YamlPropertySourceFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import java.util.List;
import java.util.Map;


@Configuration
@PropertySource(value = "classpath:url-map-to-db.yml", ignoreResourceNotFound = true, factory = YamlPropertySourceFactory.class)
@ConfigurationProperties(prefix = "mapping")
public class UrlMapToDatabase {
    public Map<String, List<String>> dbToUrl;

    public Map<String, List<String>> getDbToUrl() {
        return dbToUrl;
    }

    public void setDbToUrl(Map<String, List<String>> dbToUrl) {
        this.dbToUrl = dbToUrl;
    }
}
