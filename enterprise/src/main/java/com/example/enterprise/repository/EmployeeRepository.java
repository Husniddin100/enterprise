package com.example.enterprise.repository;

import com.example.enterprise.entity.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<EmployeeEntity,Integer> {
}
