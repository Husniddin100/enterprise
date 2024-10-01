package com.example.enterprise.service;

import com.example.enterprise.dto.CalculationTableDTO;
import com.example.enterprise.dto.EmployeeDTO;
import com.example.enterprise.entity.CalculationTableEntity;
import com.example.enterprise.entity.EmployeeEntity;
import com.example.enterprise.exp.AppBadException;
import com.example.enterprise.repository.CalculationTableRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class CalculationTableService {
    @Autowired
    private CalculationTableRepository calculationTableRepository;

    public CalculationTableDTO create(CalculationTableDTO dto) {
        CalculationTableEntity entity = new CalculationTableEntity();
        entity.setEmployeeId(dto.getEmployeeId());
        entity.setAmount(dto.getAmount());
        entity.setRate(dto.getRate());
        entity.setDate(LocalDate.now());
        entity.setOrganizationId(dto.getOrganizationId());
        entity.setCalculationType(dto.getCalculationType());

        calculationTableRepository.save(entity);
        log.info("Calculation table created");

        dto.setId(entity.getId());
        dto.setDate(String.valueOf(entity.getDate()));
        return dto;
    }

    public CalculationTableDTO getById(Integer id) {
        var calculationTable = calculationTableRepository.findById(id)
                .orElse(null);
        if (calculationTable == null) {
            log.warn("calculation with id {} not found", id);
            throw new AppBadException("employee not found");
        }
        return toDto(calculationTable);
    }

    public CalculationTableDTO update(Integer id, CalculationTableDTO dto) {
        CalculationTableEntity calculationTable = calculationTableRepository.findById(id)
                .orElse(null);

        if (calculationTable == null) {
            throw new AppBadException("Employee not found");
        }
        calculationTable.setAmount(dto.getAmount());
        calculationTable.setCalculationType(dto.getCalculationType());
        calculationTable.setDate(LocalDate.now());
        calculationTable.setEmployeeId(dto.getEmployeeId());
        calculationTable.setOrganizationId(dto.getOrganizationId());
        calculationTable.setRate(dto.getRate());
        calculationTableRepository.save(calculationTable);
        log.info("Calculation table updated{}", id);
        return toDto(calculationTable);
    }

    public Boolean delete(Integer id) {
        if (!calculationTableRepository.existsById(id)) {
            throw new AppBadException("calculation not found");
        }
        calculationTableRepository.deleteById(id);
        return true;
    }

    public List<Object[]> getEmployeesByMonthAndRate(LocalDate startDate, LocalDate endDate, Double rate) {
        return calculationTableRepository.findByMonthAndRate(startDate, endDate, rate);
    }

    public List<Object[]> getEmployeesWithMultipleRegions(LocalDate startDate, LocalDate endDate) {
        return calculationTableRepository.findEmployeesWithMultipleRegions(startDate, endDate);
    }


    public CalculationTableDTO toDto(CalculationTableEntity calculationTable) {
        CalculationTableDTO dto = new CalculationTableDTO();
        dto.setAmount(calculationTable.getAmount());
        dto.setCalculationType(calculationTable.getCalculationType());
        dto.setDate(String.valueOf(calculationTable.getDate()));
        dto.setEmployeeId(calculationTable.getEmployeeId());
        dto.setId(calculationTable.getId());
        dto.setOrganizationId(calculationTable.getOrganizationId());
        dto.setRate(calculationTable.getRate());
        return dto;
    }


}
