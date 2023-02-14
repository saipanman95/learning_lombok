package com.cwm.codingwithmike.repository;

import com.cwm.codingwithmike.dao.EmployeeImage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeImageRepository extends CrudRepository<EmployeeImage, Long> {
    EmployeeImage findByEmployeeId(final Long employeeId);
    List<EmployeeImage> findAll();
}
