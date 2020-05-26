package com.csipl.lpg.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.csipl.lpg.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long>{

}
