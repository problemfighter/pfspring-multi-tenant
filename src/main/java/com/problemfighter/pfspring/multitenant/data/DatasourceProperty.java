package com.problemfighter.pfspring.multitenant.data;

import lombok.Data;


@Data
public class DatasourceProperty {

    public String driverClassName;
    public String url;
    public String username;
    public String password;



}
