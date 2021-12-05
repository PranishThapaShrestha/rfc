package com.nicasia.rfc.usermanagement.designation.service;

import com.nicasia.rfc.usermanagement.designation.dto.DesignationRequest;
import com.nicasia.rfc.usermanagement.designation.dto.DesignationResource;
import com.nicasia.rfc.usermanagement.designation.entity.Designation;

import java.util.List;

public interface DesignationService {

    DesignationResource findByDesignationName(String name);

    Designation findById(Long id);

    List<DesignationResource> findAllDesignation();

    DesignationResource updateDesignation(Long id, DesignationRequest designationRequest);

    DesignationResource addNewDesignation(DesignationRequest designationRequest);

    void removeDesignation(DesignationRequest designationRequest);


}
