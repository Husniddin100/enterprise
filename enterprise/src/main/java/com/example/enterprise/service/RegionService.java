package com.example.enterprise.service;

import com.example.enterprise.dto.RegionDTO;
import com.example.enterprise.entity.RegionEntity;
import com.example.enterprise.exp.AppBadException;
import com.example.enterprise.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public RegionEntity createRegion(RegionDTO dto) {
        RegionEntity entity = new RegionEntity();
        entity.setName(dto.getName());
        return regionRepository.save(entity);
    }


    public Iterable<RegionEntity> getAllRegions() {
        return regionRepository.findAll();
    }

    public RegionEntity getRegionById(Integer id) {
        Optional<RegionEntity> optional = regionRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadException("region not found");
        }
        return optional.get();
    }

    public RegionEntity updateRegion(Integer id, RegionDTO dto) {
        RegionEntity entity = getRegionById(id);
        entity.setName(dto.getName());
        return regionRepository.save(entity);
    }

    public Boolean deleteRegion(Integer id) {
        getRegionById(id);
        regionRepository.deleteById(id);
        return true;
    }
}