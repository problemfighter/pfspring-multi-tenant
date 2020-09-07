package com.problemfighter.pfspring.multitenant.model.entity;

public enum InstanceStatus {
    ACTIVE("ACTIVE"),
    SUSPEND("SUSPEND"),
    REMOVED("SUSPEND");

    private final String title;

    InstanceStatus(String title) {
        this.title = title;
    }
}
