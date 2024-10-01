package com.example.enterprise.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class EmployeeDTO {
    private Integer id;

    private String firstName;

    private String lastName;

    private String pinfl;

    private Integer organizationId;

    private String hireDate;



}
