package com.problemfighter.pfspring.multitenant.datasource.processor;

import com.problemfighter.pfspring.multitenant.common.MTConstant;
import com.problemfighter.pfspring.multitenant.datasource.holder.DatabaseIdentifierHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MultiDatasourceRouter extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        Object dbKey = DatabaseIdentifierHolder.getTenantId();
        if (dbKey == null) {
            dbKey = MTConstant.defaultKey;
        }
        return dbKey;
    }

}
