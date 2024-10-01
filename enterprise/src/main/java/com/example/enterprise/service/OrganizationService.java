package com.example.enterprise.service;

import com.example.enterprise.dto.OrganizationDTO;
import com.example.enterprise.entity.OrganizationEntity;
import com.example.enterprise.exp.AppBadException;
import com.example.enterprise.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;

    public OrganizationDTO createOrganization(OrganizationDTO dto) {
        OrganizationEntity entity = new OrganizationEntity();
        entity.setName(dto.getName());
        entity.setRegionId(dto.getRegionId());
        organizationRepository.save(entity);

        dto.setId(entity.getId());
        return dto;
    }


    public Iterable<OrganizationEntity> getAllOrganizations() {
        return organizationRepository.findAll();
    }


    public OrganizationEntity getOrganizationById(Integer id) {
        Optional<OrganizationEntity> optional = organizationRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadException("organization not found");
        }

        return optional.get();
    }

    public OrganizationDTO updateOrganization(Integer id, OrganizationDTO dto) {

        OrganizationEntity entity = getOrganizationById(id);
        entity.setName(dto.getName());
        entity.setRegionId(dto.getRegionId());
        organizationRepository.save(entity);

        dto.setId(entity.getId());
        return dto;
    }

    public Boolean deleteOrganization(Integer id) {
        getOrganizationById(id);
        organizationRepository.deleteById(id);
        return true;
    }
}
