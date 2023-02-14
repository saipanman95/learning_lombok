package com.cwm.codingwithmike.dto;

import com.cwm.codingwithmike.enums.EmployeeStatus;
import lombok.NonNull;

import java.util.Date;
import java.util.List;

public record EmployeeDto(Long id, @NonNull String lastName, @NonNull String firstName,
                          String middleName, String suffix, String email,
                          List<AddressDto> addresses, @NonNull EmployeeStatus employeeStatus, Date createdDate) {
}