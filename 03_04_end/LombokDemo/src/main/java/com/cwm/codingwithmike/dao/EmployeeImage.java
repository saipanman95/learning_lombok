package com.cwm.codingwithmike.dao;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class EmployeeImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Exclude
    private Long id;

    @ToString.Exclude
    @OneToOne(mappedBy = "employeeImage")
    private Employee employee;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Lob
    private byte[] image;
    private Date createdDate;
}
