package com.example.enterprise.entity;

import jakarta.persistence.*;

import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Data
@Entity
@Table(name = "employee")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String pinfl;

    @Column(name = "organization_id")
    private Integer organizationId;

    @ManyToOne
    @JoinColumn(name = "organization_id", insertable = false, updatable = false)
    private OrganizationEntity organization;

    @Column
    private LocalDate hireDate;

    @OneToMany(mappedBy = "employee")
    private List<CalculationTableEntity> calculations;
}

