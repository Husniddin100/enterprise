package com.example.enterprise.controller;

import com.example.enterprise.dto.OrganizationDTO;
import com.example.enterprise.entity.OrganizationEntity;
import com.example.enterprise.service.OrganizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/organization")
@RestController
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;

    @PostMapping("/create")
    public OrganizationDTO createOrganization(@RequestBody OrganizationDTO dto) {

        return organizationService.createOrganization(dto);
    }

    @GetMapping("/getAll")
    public Iterable<OrganizationEntity> getAllOrganization() {
        return organizationService.getAllOrganizations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizationEntity> getOrganizationById(@PathVariable Integer id) {
        OrganizationEntity entity = organizationService.getOrganizationById(id);
        return ResponseEntity.ok(entity);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<OrganizationDTO> updateOrganization(@PathVariable Integer id,
                                                                 @RequestBody OrganizationDTO dto) {
        return ResponseEntity.ok(organizationService.updateOrganization(id, dto));
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteOrganization(@PathVariable Integer id) {
        return ResponseEntity.ok(organizationService.deleteOrganization(id));
    }
}
