package com.cwm.codingwithmike.repository;

import com.cwm.codingwithmike.dao.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    List<Employee> findByLastName(String lastName);
    List<Employee> findAll();
}
