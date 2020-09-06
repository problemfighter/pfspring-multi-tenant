package com.problemfighter.pfspring.multitenant.datasource.processor;

import com.problemfighter.pfspring.multitenant.common.MTConstant;
import com.problemfighter.pfspring.multitenant.common.MultiDatasourceException;
import com.problemfighter.pfspring.multitenant.datasource.config.MultiDatabaseConfig;
import com.problemfighter.pfspring.multitenant.datasource.data.DatasourceProperty;
import com.problemfighter.pfspring.multitenant.datasource.data.InstanceInfo;
import com.problemfighter.pfspring.multitenant.datasource.holder.DatabaseIdentifierHolder;
import com.problemfighter.pfspring.multitenant.model.entity.InstanceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

@Component
public class DatasourceIdentityProcessor {

    @Autowired
    private MultiDatabaseConfig multiDatabaseConfig;

    private String getDbKeyFromRequest(WebRequest request) {
        return request.getHeader(multiDatabaseConfig.dbIdentifierKey);
    }

    private void validateProperties(String databaseKey) {
        DatasourceProperty property = DatabaseIdentifierHolder.registeredInstance.get(databaseKey);
        if (!property.instanceStatus.equals(InstanceStatus.ACTIVE)) {
            throw new MultiDatasourceException(property.instanceStatus.toString()).setInfo(new InstanceInfo(property.instanceStatus.toString(), property.message));
        }
    }

    private String urlToDatabaseKey(String databaseKey, WebRequest request) {
        return databaseKey;
    }

    public String getDatabaseKey(WebRequest request) {
        if (!multiDatabaseConfig.enable) {
            return MTConstant.defaultKey;
        }
        String databaseKey = getDbKeyFromRequest(request);
        if (databaseKey != null && !DatabaseIdentifierHolder.isRegistered(databaseKey)) {
            throw new MultiDatasourceException(MTConstant.NOT_FOUND).setStatus(MTConstant.NOT_FOUND);
        }

        databaseKey = urlToDatabaseKey(databaseKey, request);
        if (databaseKey == null) {
            return MTConstant.defaultKey;
        }
        validateProperties(databaseKey);
        return databaseKey;
    }

}
