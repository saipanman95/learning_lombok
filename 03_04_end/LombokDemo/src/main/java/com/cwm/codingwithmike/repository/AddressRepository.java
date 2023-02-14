package com.cwm.codingwithmike.repository;

import com.cwm.codingwithmike.dao.Address;
import com.cwm.codingwithmike.dao.Employee;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository extends CrudRepository<Address, Long> {
    List<Address> findAll();
    List<Address> findByCity(String city);
    List<Address> findByState(String state);

    @Modifying(clearAutomatically = true)
    @Query("update Address add set add.addressType =:addressType where add.id =:addressId")
    void updateAddressTypeById(@Param("addressId") String addressType, @Param("addressId") Long addressId);
}
