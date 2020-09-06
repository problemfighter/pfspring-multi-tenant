package com.problemfighter.pfspring.multitenant.datasource.data;

public class InstanceInfo {
    public String status;
    public String message;

    public InstanceInfo() {
    }

    public InstanceInfo(String status) {
        this.status = status;
    }

    public InstanceInfo(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
