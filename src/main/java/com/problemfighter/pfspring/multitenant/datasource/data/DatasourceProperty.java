package com.problemfighter.pfspring.multitenant.datasource.data;

import com.problemfighter.pfspring.multitenant.model.entity.InstanceStatus;
import lombok.Data;


@Data
public class DatasourceProperty {

    public String driverClassName;
    public String url;
    public String username;
    public String password;

    public InstanceStatus instanceStatus = InstanceStatus.ACTIVE;
    public String message;

}
