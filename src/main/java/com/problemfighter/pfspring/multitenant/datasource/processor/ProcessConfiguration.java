package com.problemfighter.pfspring.multitenant.datasource.processor;

import com.problemfighter.java.oc.common.ObjectCopierException;
import com.problemfighter.java.oc.copier.ObjectCopier;
import com.problemfighter.pfspring.multitenant.common.MTConstant;
import com.problemfighter.pfspring.multitenant.datasource.config.DefaultDatabaseConfig;
import com.problemfighter.pfspring.multitenant.datasource.config.MultiDatabaseConfig;
import com.problemfighter.pfspring.multitenant.datasource.data.DatasourceProperty;
import com.problemfighter.pfspring.multitenant.datasource.holder.DatabaseIdentifierHolder;
import com.problemfighter.pfspring.multitenant.model.entity.MultiDatabaseIdentity;
import com.problemfighter.pfspring.multitenant.service.MultiDatabaseIdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class ProcessConfiguration {

    @Autowired
    private DefaultDatabaseConfig defaultDatabaseConfig;

    @Autowired
    private MultiDatabaseConfig multiDatabaseConfig;

    @Autowired
    private MultiDatabaseIdentityService multiDatabaseIdentityService;

    private DataSource createDatasource(DatasourceProperty datasourceProperty) {
        if (datasourceProperty.url == null || datasourceProperty.username == null || datasourceProperty.password == null) {
            return null;
        }
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(datasourceProperty.url);
        dataSourceBuilder.username(datasourceProperty.username);
        dataSourceBuilder.password(datasourceProperty.password);

        if (datasourceProperty.driverClassName != null) {
            dataSourceBuilder.driverClassName(datasourceProperty.driverClassName);
        }
        return dataSourceBuilder.build();
    }

    private DatasourceProperty entityToDatasourceProperty(MultiDatabaseIdentity multiDatabaseIdentity) throws ObjectCopierException {
        ObjectCopier objectCopier = new ObjectCopier();
        return objectCopier.copy(multiDatabaseIdentity, DatasourceProperty.class);
    }

    private Map<Object, Object> getDatabaseRegisteredDatasource() {
        Map<Object, Object> sourceMap = new LinkedHashMap<>();
        try {
            Iterable<MultiDatabaseIdentity> list = multiDatabaseIdentityService.getAllDatabaseIdentities();
            if (list != null) {
                DataSource dataSource;
                DatasourceProperty datasourceProperty;
                for (MultiDatabaseIdentity multiDatabaseIdentity : list) {
                    if (multiDatabaseIdentity.instanceKey != null) {
                        datasourceProperty = entityToDatasourceProperty(multiDatabaseIdentity);
                        dataSource = createDatasource(datasourceProperty);
                        if (dataSource != null) {
                            sourceMap.put(multiDatabaseIdentity.instanceKey, dataSource);
                            DatabaseIdentifierHolder.registeredInstance.put(multiDatabaseIdentity.instanceKey, datasourceProperty);
                        }
                    }
                }
            }
        } catch (Exception ignore) {
        }
        return sourceMap;
    }

    private Map<Object, Object> getApplicationConfigRegisteredDatasource(Map<Object, Object> existingConfig) {
        if (multiDatabaseConfig.datasources != null && !multiDatabaseConfig.datasources.isEmpty()) {
            DataSource dataSource;
            for (Map.Entry<String, DatasourceProperty> entry : multiDatabaseConfig.datasources.entrySet()) {
                if (entry.getKey() != null) {
                    dataSource = createDatasource(entry.getValue());
                    if (dataSource != null) {
                        existingConfig.put(entry.getKey(), dataSource);
                        DatabaseIdentifierHolder.registeredInstance.put(entry.getKey(), entry.getValue());
                    }
                }
            }
        }
        return existingConfig;
    }

    public Map<Object, Object> getAllRegisteredDatasource() {
        Map<Object, Object> sourceMap = getDatabaseRegisteredDatasource();
        getApplicationConfigRegisteredDatasource(sourceMap);
        sourceMap.put(MTConstant.defaultKey, getDefaultDatasource());
        return sourceMap;
    }

    public Boolean isMultiDatasourceEnable() {
        return multiDatabaseConfig.enable;
    }

    public DataSource getDefaultDatasource() {
        return createDatasource(defaultDatabaseConfig.getDatasource());
    }

}
