package com.example.enterprise.dto;

import com.example.enterprise.enums.CalculationType;
import lombok.Data;


@Data
public class CalculationTableDTO {
    private Integer id;
    private Integer employeeId;
    private double amount;
    private double rate;
    private String date;
    private Integer organizationId;
    private CalculationType calculationType;
}
