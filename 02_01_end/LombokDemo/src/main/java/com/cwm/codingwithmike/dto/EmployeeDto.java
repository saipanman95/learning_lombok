package com.cwm.codingwithmike.dto;

import com.cwm.codingwithmike.enums.EmployeeStatus;

import java.util.Date;
import java.util.List;

public record EmployeeDto(Long id, String lastName, String firstName,
                          String middleName, String suffix, String email,
                          List<AddressDto> addresses, EmployeeStatus employeeStatus, Date createdDate) {
}