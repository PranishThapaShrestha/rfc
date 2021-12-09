package com.nicasia.rfc.core.usermanagement.designation.service;

import com.nicasia.rfc.core.usermanagement.designation.entity.Designation;
import com.nicasia.rfc.core.usermanagement.designation.dto.DesignationRequest;
import com.nicasia.rfc.core.usermanagement.designation.dto.DesignationResource;

import java.util.List;

public interface DesignationService {

    DesignationResource findByDesignationName(String name);

    Designation findById(Long id);

    List<DesignationResource> findAllDesignation();

    DesignationResource updateDesignation(Long id, DesignationRequest designationRequest);

    DesignationResource addNewDesignation(DesignationRequest designationRequest);

    void removeDesignation(DesignationRequest designationRequest);


}
