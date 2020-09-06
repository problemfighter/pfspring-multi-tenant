package com.problemfighter.pfspring.multitenant.model.entity;

import javax.persistence.*;

@Entity
public class MultiDatabaseIdentity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(unique = true)
    public String instanceKey;

    public InstanceStatus instanceStatus = InstanceStatus.ACTIVE;

    @Column(columnDefinition = "TEXT")
    public String message;

    @Column(nullable = false)
    public String url;

    @Column(nullable = false)
    public String username;

    @Column(nullable = false)
    public String password;

    public String driverClassName;
}
