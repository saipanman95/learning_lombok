package com.cwm.codingwithmike.dao;

import com.cwm.codingwithmike.enums.EmployeeStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Exclude
    private Long id;
    @NonNull
    private String lastName;
    @NonNull
    private String firstName;
    private String middleName;
    private String suffix;
    @NonNull
    private String email;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Address> addresses;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private EmployeeImage employeeImage;
    @NonNull
    @Builder.Default
    @Enumerated(EnumType.ORDINAL)
    private EmployeeStatus employeeStatus = EmployeeStatus.FULLTIME;
    @NonNull
    @Builder.Default
    private Date createdDate = new Date();
}
