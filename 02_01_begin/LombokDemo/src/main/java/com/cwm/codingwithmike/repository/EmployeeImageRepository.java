package com.cwm.codingwithmike.repository;

import com.cwm.codingwithmike.dao.EmployeeImage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeImageRepository extends CrudRepository<EmployeeImage, Long> {
    EmployeeImage findByEmployeeId(final Long employeeId);
    List<EmployeeImage> findAll();
}
