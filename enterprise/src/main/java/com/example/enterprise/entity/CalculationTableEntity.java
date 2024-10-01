package com.example.enterprise.entity;


import com.example.enterprise.enums.CalculationType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Data
@Entity
@Table(name = "calculation")
public class CalculationTableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "employee_id")
    private Integer employeeId;

    @ManyToOne
    @JoinColumn(name = "employee_id", insertable = false, updatable = false)
    private EmployeeEntity employee;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "rate")
    private Double rate;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "organization_id")
    private Integer organizationId;

    @ManyToOne
    @JoinColumn(name = "organization_id", insertable = false, updatable = false)
    private OrganizationEntity organization;

    @Column(name = "calculation_type")
    private CalculationType calculationType;
}
