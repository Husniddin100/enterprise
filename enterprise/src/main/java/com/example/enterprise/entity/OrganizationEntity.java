package com.example.enterprise.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
@Entity
@Table(name = "organization")
public class OrganizationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column(name = "region_id")
    private Integer regionId;

    @ManyToOne
    @JoinColumn(name = "region_id", updatable = false, insertable = false)
    private RegionEntity region;

    @Column(name = "parent_id")
    private Integer parentId;

    @ManyToOne
    @JoinColumn(name = "parent_id", insertable = false, updatable = false)
    private OrganizationEntity parent;

    @OneToMany(mappedBy = "organization")
    private List<EmployeeEntity> employees;

    @OneToMany(mappedBy = "organization")
    private List<CalculationTableEntity> calculations;

    @OneToMany(mappedBy = "parent")
    private List<OrganizationEntity> subOrganizations;
}
