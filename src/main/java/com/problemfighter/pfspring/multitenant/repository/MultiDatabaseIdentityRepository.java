package com.problemfighter.pfspring.multitenant.repository;

import com.problemfighter.pfspring.multitenant.model.entity.MultiDatabaseIdentity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MultiDatabaseIdentityRepository extends CrudRepository<MultiDatabaseIdentity, Long> {
}
