package com.problemfighter.pfspring.multitenant.service;

import com.problemfighter.pfspring.multitenant.model.entity.InstanceStatus;
import com.problemfighter.pfspring.multitenant.model.entity.MultiDatabaseIdentity;
import com.problemfighter.pfspring.multitenant.repository.MultiDatabaseIdentityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MultiDatabaseIdentityService {

    @Autowired
    private MultiDatabaseIdentityRepository multiDatabaseIdentityRepository;

    public Iterable<MultiDatabaseIdentity> getAllDatabaseIdentities(){
        return multiDatabaseIdentityRepository.findAll();
    }

    public void save(){
        MultiDatabaseIdentity multiDatabaseIdentity = new MultiDatabaseIdentity();
        multiDatabaseIdentity.driverClassName = "com.mysql.cj.jdbc.Driver";
        multiDatabaseIdentity.username = "root";
        multiDatabaseIdentity.url = "jdbc:mysql://localhost:3306/dbsource";
        multiDatabaseIdentity.password = "";
        multiDatabaseIdentity.instanceKey = "dbsource";
        multiDatabaseIdentity.instanceStatus = InstanceStatus.ACTIVE;
        multiDatabaseIdentityRepository.save(multiDatabaseIdentity);
        System.out.println("=-=-=-=-=-=-=" + multiDatabaseIdentity.id);
    }
}
