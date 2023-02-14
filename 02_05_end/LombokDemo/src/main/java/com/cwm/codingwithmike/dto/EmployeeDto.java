package com.cwm.codingwithmike.dto;

import com.cwm.codingwithmike.enums.EmployeeStatus;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.With;

import java.util.Date;
import java.util.List;

public record EmployeeDto(Long id, @NonNull String lastName, @NonNull String firstName,
                          String middleName, String suffix, String email,
                          List<AddressDto> addresses, @With @NonNull EmployeeStatus employeeStatus, Date createdDate) {
}