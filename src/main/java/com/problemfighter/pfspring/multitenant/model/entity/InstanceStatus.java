package com.problemfighter.pfspring.multitenant.model.entity;

public enum InstanceStatus {
    ACTIVE("ACTIVE"),
    SUSPEND("SUSPEND"),
    REMOVED("SUSPEND");

    private final String title;

    InstanceStatus(String title) {
        this.title = title;
    }

    public static InstanceStatus status(Integer index) {
        try {
            return values()[index];
        } catch (Exception ignore) {
        }
        return null;
    }
}
