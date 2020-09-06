package com.problemfighter.pfspring.multitenant.common;


import com.problemfighter.pfspring.multitenant.datasource.data.InstanceInfo;

/**
 * Created by Touhid Mia on 11/09/2014.
 */
public class MultiDatasourceException extends RuntimeException {


    public InstanceInfo instanceInfo;

    public MultiDatasourceException() {
        super("Multi Datasource Exception");
    }

    public MultiDatasourceException(String message) {
        super(message);
    }

    public MultiDatasourceException setInfo(InstanceInfo instanceInfo) {
        this.instanceInfo = instanceInfo;
        return this;
    }

    public MultiDatasourceException setStatus(String status) {
        this.instanceInfo = new InstanceInfo(status);
        return this;
    }
}
