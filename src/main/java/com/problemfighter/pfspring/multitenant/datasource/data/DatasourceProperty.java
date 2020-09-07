package com.problemfighter.pfspring.multitenant.datasource.data;

import com.problemfighter.pfspring.multitenant.model.entity.InstanceStatus;
import lombok.Data;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;


@Data
public class DatasourceProperty extends DataSourceProperties {

    public InstanceStatus instanceStatus = InstanceStatus.ACTIVE;
    public String message;
    public String instanceKey;

}
