package com.problemfighter.pfspring.multitenant.datasource.config;


import com.problemfighter.pfspring.common.factory.YamlPropertySourceFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Configuration
@PropertySource(value = "classpath:url-map-to-db.yml", ignoreResourceNotFound = true, factory = YamlPropertySourceFactory.class)
@ConfigurationProperties(prefix = "mapping")
public class UrlMapToDatabase {

    public LinkedHashMap<String, String> regularMapping = new LinkedHashMap<>();

    public LinkedHashMap<String, String> regexMapping = new LinkedHashMap<>();


    private Boolean isRegexUrl(String url) {
        if (url != null && url.endsWith("*")) {
            return true;
        }
        return false;
    }

    public void setDbToUrl(Map<String, List<String>> dbToUrl) {
        for (Map.Entry<String, List<String>> entry : dbToUrl.entrySet()) {
            if (entry.getValue().size() != 0) {
                for (String url : entry.getValue()) {
                    if (isRegexUrl(url)) {
                        regexMapping.put(url, entry.getKey());
                    } else {
                        regularMapping.put(url, entry.getKey());
                    }
                }
            }
        }
    }
}
