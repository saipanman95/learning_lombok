package com.cwm.codingwithmike.dto;

import lombok.Value;

import java.util.Date;
import java.util.List;

@Value
public class EmployeeDto {
    private final Long id;
    private final String lastName;
    private final String firstName;
    private final String middleName;
    private final String suffix;
    private final List<AddressDto> addresses;
    private final Date createdDate;

}
