package com.example.enterprise.controller;

import com.example.enterprise.dto.CalculationTableDTO;
import com.example.enterprise.service.CalculationTableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/calculation")
public class CalculationTableController {
    @Autowired
    private CalculationTableService calculationTableService;

    @PostMapping("/create")
    public CalculationTableDTO create(@RequestBody CalculationTableDTO dto) {
        return calculationTableService.create(dto);
    }

    @GetMapping("/get-byID/{id}")
    public CalculationTableDTO getById(@PathVariable Integer id) {
        return calculationTableService.getById(id);
    }

    @PutMapping("/update/{id}")
    public CalculationTableDTO update(@PathVariable Integer id,
                                      @RequestBody CalculationTableDTO dto) {
        return calculationTableService.update(id, dto);
    }

    @PutMapping("/delete/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return calculationTableService.delete(id);
    }

    @GetMapping("/employees/rate")
    public List<Object[]> getEmployeesByRate(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam("rate") Double rate) {

        return calculationTableService.getEmployeesByMonthAndRate(startDate, endDate, rate);
    }
    @GetMapping("/employees/multiple-regions")
    public List<Object[]> getEmployeesWithMultipleRegions(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        return calculationTableService.getEmployeesWithMultipleRegions(startDate, endDate);
    }


}
