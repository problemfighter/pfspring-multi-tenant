package com.problemfighter.pfspring.multitenant.service;

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
}
