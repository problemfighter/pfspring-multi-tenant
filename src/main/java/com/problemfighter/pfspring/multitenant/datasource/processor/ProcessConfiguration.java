package com.problemfighter.pfspring.multitenant.datasource.processor;

import com.problemfighter.java.oc.common.ObjectCopierException;
import com.problemfighter.java.oc.copier.ObjectCopier;
import com.problemfighter.pfspring.multitenant.common.MTConstant;
import com.problemfighter.pfspring.multitenant.datasource.config.DefaultDatabaseConfig;
import com.problemfighter.pfspring.multitenant.datasource.config.MultiDatabaseConfig;
import com.problemfighter.pfspring.multitenant.datasource.data.DatasourceProperty;
import com.problemfighter.pfspring.multitenant.datasource.holder.DatabaseIdentifierHolder;
import com.problemfighter.pfspring.multitenant.model.entity.InstanceStatus;
import com.problemfighter.pfspring.multitenant.model.entity.MultiDatabaseIdentity;
import com.problemfighter.pfspring.multitenant.service.MultiDatabaseIdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class ProcessConfiguration {

    @Autowired
    private DefaultDatabaseConfig defaultDatabaseConfig;

    @Autowired
    private MultiDatabaseConfig multiDatabaseConfig;

    private final Map<Object, Object> registeredDataSources = new LinkedHashMap<>();

    private DataSource createDatasource(DatasourceProperty datasourceProperty) {
        return datasourceProperty.initializeDataSourceBuilder().build();
    }

    private void registerDataSource(DatasourceProperty datasourceProperty){
        DataSource dataSource = createDatasource(datasourceProperty);
        if (dataSource != null) {
            registeredDataSources.put(datasourceProperty.instanceKey, dataSource);
            DatabaseIdentifierHolder.registeredInstance.put(datasourceProperty.instanceKey, datasourceProperty);
        }
    }

    private DatasourceProperty mapDbTableToDatasourceProperty(ResultSet resultSet) throws SQLException {
        DatasourceProperty datasourceProperty = null;
        if (resultSet != null) {
            datasourceProperty = new DatasourceProperty();
            datasourceProperty.setUrl(resultSet.getString("url"));
            datasourceProperty.setUsername(resultSet.getString("username"));
            datasourceProperty.setPassword(resultSet.getString("password"));
            String driverClassName = resultSet.getString("driver_class_name");
            if (driverClassName != null) {
                datasourceProperty.setDriverClassName(driverClassName);
            }
            datasourceProperty.instanceKey = resultSet.getString("instance_key");
            datasourceProperty.message = resultSet.getString("message");
            datasourceProperty.instanceStatus = InstanceStatus.status(resultSet.getInt("instance_status"));
        }
        return datasourceProperty;
    }


    private void registeredAllDatasourceFromDB() {
        try {
            Connection connection = getDefaultDatasource().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM multi_database_identity");
            DatasourceProperty datasourceProperty = null;
            while (resultSet.next()) {
                datasourceProperty = mapDbTableToDatasourceProperty(resultSet);
                if (datasourceProperty != null) {
                    registerDataSource(datasourceProperty);
                }
            }
        } catch (SQLException throwables) {
            System.out.println("Datasource Registration Error: " + throwables.getMessage());
        }
    }

    private void registeredAllDatasourceFromApplicationConfig() {
        if (multiDatabaseConfig.datasources != null && !multiDatabaseConfig.datasources.isEmpty()) {
            for (Map.Entry<String, DatasourceProperty> entry : multiDatabaseConfig.datasources.entrySet()) {
                if (entry.getKey() != null) {
                    entry.getValue().instanceKey = entry.getKey();
                    registerDataSource(entry.getValue());
                }
            }
        }
    }

    public Map<Object, Object> getAllRegisteredDatasource() {
        registeredAllDatasourceFromDB();
        registeredAllDatasourceFromApplicationConfig();
        return registeredDataSources;
    }

    public Boolean isMultiDatasourceEnable() {
        return multiDatabaseConfig.enable;
    }

    public DataSource getDefaultDatasource() {
        return createDatasource(defaultDatabaseConfig.getDatasource());
    }

}
