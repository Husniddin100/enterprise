package com.example.enterprise.service;

import com.example.enterprise.dto.EmployeeDTO;
import com.example.enterprise.entity.EmployeeEntity;
import com.example.enterprise.exp.AppBadException;
import com.example.enterprise.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;


    public EmployeeEntity createEmployee(EmployeeDTO employeeDTO) {
        EmployeeEntity employee = new EmployeeEntity();
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setPinfl(employeeDTO.getPinfl());
        employee.setOrganizationId(employeeDTO.getOrganizationId());
        employee.setHireDate(LocalDate.now());

        return employeeRepository.save(employee);
    }


    public EmployeeDTO updateEmployee(Integer id, EmployeeDTO employeeDTO) {
        EmployeeEntity employee = employeeRepository.findById(id)
                .orElse(null);

        if (employee == null) {
            throw new AppBadException("Employee not found");
        }

        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setPinfl(employeeDTO.getPinfl());
        employee.setOrganizationId(employeeDTO.getOrganizationId());
        employee.setHireDate(LocalDate.now());
        log.info("Updating employee {} {}", employee.getFirstName(), employee.getLastName());
        return toDto(employeeRepository.save(employee));
    }


    public List<EmployeeDTO> getAllEmployees() {
        var employees = employeeRepository.findAll();

        List<EmployeeDTO> employeeDTOS = new ArrayList<>();

        for (EmployeeEntity employee : employees) {
            employeeDTOS.add(toDto(employee));
        }

        return employeeDTOS;
    }

    public EmployeeDTO getEmployeeById(Integer id) {
        var employee = employeeRepository.findById(id)
                .orElse(null);
        if (employee == null) {
            log.warn("Employee with id {} not found", id);
            throw new AppBadException("employee not found");
        }
        return toDto(employee);
    }

    public Boolean deleteEmployee(Integer id) {
        if (!employeeRepository.existsById(id)) {
            throw new AppBadException("employee not found");
        }
        log.info("Deleting employee {}", id);
        employeeRepository.deleteById(id);
        return true;
    }

    public EmployeeDTO toDto(EmployeeEntity employee) {
        return new EmployeeDTO()
                .setId(employee.getId())
                .setFirstName(employee.getFirstName())
                .setLastName(employee.getLastName())
                .setPinfl(employee.getPinfl())
                .setOrganizationId(employee.getOrganizationId())
                .setHireDate(String.valueOf(employee.getHireDate()));
    }

}
