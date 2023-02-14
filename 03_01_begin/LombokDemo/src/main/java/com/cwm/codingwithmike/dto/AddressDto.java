package com.cwm.codingwithmike.dto;

import lombok.NonNull;
import lombok.With;

import java.util.Date;

public record AddressDto(Long id, @NonNull String addressType, @NonNull String addressLine1, String addressLine2,
                         @NonNull String city, @NonNull String state, @NonNull String zipCode,
                         @NonNull Date createdDate) {

}
