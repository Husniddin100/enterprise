package com.example.enterprise.repository;

import com.example.enterprise.entity.OrganizationEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrganizationRepository extends CrudRepository<OrganizationEntity,Integer> {
}
