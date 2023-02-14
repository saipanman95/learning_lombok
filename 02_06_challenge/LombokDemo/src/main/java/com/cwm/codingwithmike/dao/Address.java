package com.cwm.codingwithmike.dao;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Objects;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Exclude
    private Long id;
    @NonNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name= "employee_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Employee employee;
    @NonNull
    @Builder.Default
    private String addressType = "home";
    @NonNull
    private String addressLine1;
    private String addressLine2;
    @NonNull
    private String city;
    @NonNull
    private String state;
    @NonNull
    private String zipCode;
    @NonNull
    @Builder.Default
    private Date createdDate = new Date();

}
