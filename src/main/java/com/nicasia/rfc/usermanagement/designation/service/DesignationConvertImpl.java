package com.nicasia.rfc.usermanagement.designation.service;

import com.nicasia.rfc.usermanagement.designation.dto.DesignationResource;
import com.nicasia.rfc.usermanagement.designation.entity.Designation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DesignationConvertImpl implements DesignationConvert {

    @Override
    public DesignationResource convertDesignation(Designation designation) {

        return covertDesignationHelper(designation);
    }

    public DesignationResource covertDesignationHelper(Designation designation) {
        return DesignationResource.builder()
                .id(designation.getId())
                .name(designation.getDesgname())
                .code(designation.getDesgcode())
                .created(designation.getCreatedAt()).build();
    }

    @Override
    public List<DesignationResource> convertAllDesignationResource(List<Designation> allDesignation) {
        return allDesignation.stream().map
                (designation -> covertDesignationHelper(designation)).collect(Collectors.toList());

    }
}
