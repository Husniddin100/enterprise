package com.example.enterprise.controller;

import com.example.enterprise.dto.EmployeeDTO;
import com.example.enterprise.entity.EmployeeEntity;
import com.example.enterprise.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Operation(summary = "Api for create employee",description = "this api used for creating employee")
    @PostMapping("/create")
    public EmployeeEntity createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        log.info("REQUEST create employee : {}", employeeDTO);
        return employeeService.createEmployee(employeeDTO);
    }

    @GetMapping("/get-all")
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Integer id) {
        var employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Integer id,
                                                      @RequestBody EmployeeDTO dto) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, dto));
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteEmployee(@PathVariable Integer id) {
        return ResponseEntity.ok(employeeService.deleteEmployee(id));
    }

}