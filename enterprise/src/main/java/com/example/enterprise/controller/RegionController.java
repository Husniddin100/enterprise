package com.example.enterprise.controller;


import com.example.enterprise.dto.RegionDTO;
import com.example.enterprise.entity.RegionEntity;
import com.example.enterprise.service.RegionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/region")
public class RegionController {
    @Autowired
    private RegionService regionService;


    @PostMapping("/create")
    public RegionEntity createRegion(@RequestBody RegionDTO dto){
        log.info("Create Region");
        return regionService.createRegion(dto);
    }
    @GetMapping("/getAll")
    public Iterable<RegionEntity> getAllRegions() {
        return regionService.getAllRegions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegionEntity> getRegionById(@PathVariable Integer id) {
        RegionEntity region = regionService.getRegionById(id);
        return ResponseEntity.ok(region);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RegionEntity> updateRegion(@PathVariable Integer id,
                                                         @RequestBody RegionDTO dto) {
        return ResponseEntity.ok(regionService.updateRegion(id, dto));
    }
    @PutMapping("/delete/{id}")
    public ResponseEntity<Boolean>deleteRegion(@PathVariable Integer id){
        return ResponseEntity.ok(regionService.deleteRegion(id));
    }
}
