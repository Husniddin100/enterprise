package com.example.enterprise.repository;

import com.example.enterprise.entity.CalculationTableEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CalculationTableRepository extends CrudRepository<CalculationTableEntity, Integer> {

    @Query("SELECT e.pinfl, SUM(c.rate) AS totalRate FROM CalculationTableEntity c " +
            "JOIN c.employee e " +
            "WHERE c.date BETWEEN :startDate AND :endDate " +
            "AND c.rate > :rate " +
            "GROUP BY e.pinfl " +
            "HAVING COUNT(e.pinfl) > 1")
    List<Object[]> findByMonthAndRate(@Param("startDate") LocalDate startDate,
                                      @Param("endDate") LocalDate endDate,
                                      @Param("rate") Double rate);



    @Query("SELECT e.pinfl, COUNT(DISTINCT o.id) AS totalOrganizations, SUM(c.amount) AS totalSalary " +
            "FROM CalculationTableEntity c " +
            "JOIN c.employee e " +
            "JOIN c.organization o " +
            "JOIN o.region r " +
            "WHERE c.date BETWEEN :startDate AND :endDate " +
            "GROUP BY e.pinfl " +
            "HAVING COUNT(DISTINCT r.id) > 1")
    List<Object[]> findEmployeesWithMultipleRegions(@Param("startDate") LocalDate startDate,
                                                    @Param("endDate") LocalDate endDate);


}