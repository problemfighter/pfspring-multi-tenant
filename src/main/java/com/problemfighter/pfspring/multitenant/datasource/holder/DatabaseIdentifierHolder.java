package com.problemfighter.pfspring.multitenant.datasource.holder;

import com.problemfighter.pfspring.multitenant.datasource.data.DatasourceProperty;

import java.util.LinkedHashMap;

public class DatabaseIdentifierHolder {

    public static LinkedHashMap<String, DatasourceProperty> registeredInstance = new LinkedHashMap<>();

    private static ThreadLocal<String> currentTenant = new ThreadLocal<>();

    public static void setTenantId(String tenantId) {
        currentTenant.set(tenantId);
    }

    public static String getTenantId() {
        return currentTenant.get();
    }

    public static void clear() {
        currentTenant.remove();
    }


    public static Boolean isRegistered(String key) {
        if (registeredInstance.get(key) != null) {
            return true;
        }
        return false;
    }


}
