package com.example.enterprise.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "region")
public class RegionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;

    @OneToMany(mappedBy = "region")
    private List<OrganizationEntity> organizations;
}
