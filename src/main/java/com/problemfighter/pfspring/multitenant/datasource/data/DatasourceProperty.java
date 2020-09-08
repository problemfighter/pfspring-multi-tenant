package com.problemfighter.pfspring.multitenant.datasource.data;

import com.problemfighter.pfspring.multitenant.model.entity.InstanceStatus;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;


public class DatasourceProperty extends DataSourceProperties {

    public InstanceStatus instanceStatus = InstanceStatus.ACTIVE;
    public String message;
    public String instanceKey;

    public InstanceStatus getInstanceStatus() {
        return instanceStatus;
    }

    public void setInstanceStatus(InstanceStatus instanceStatus) {
        this.instanceStatus = instanceStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getInstanceKey() {
        return instanceKey;
    }

    public void setInstanceKey(String instanceKey) {
        this.instanceKey = instanceKey;
    }
}
