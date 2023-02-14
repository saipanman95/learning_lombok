package com.cwm.codingwithmike.dao;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List; 

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Setter
@Getter
@Entity
public class Employee {
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String lastName;
    private String firstName;
    private String middleName;
    private String suffix;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Address> addresses;
    private Date createdDate;

    public Employee(String lastName, String firstName, String middleName, String suffix, Date createdDate) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.suffix = suffix;
        this.createdDate = createdDate;
    }
}
