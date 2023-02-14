package com.cwm.codingwithmike.repository;

import com.cwm.codingwithmike.dao.Address;
import com.cwm.codingwithmike.dao.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AddressRepository extends CrudRepository<Address, Long> {
    List<Address> findAll();
    List<Address> findByCity(String city);
    List<Address> findByState(String state);
}
