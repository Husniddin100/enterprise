package com.example.enterprise.dto;


import lombok.Data;

@Data
public class OrganizationDTO {
    private Integer id;
    private String name;
    private Integer regionId;
    private Integer parent;
}
