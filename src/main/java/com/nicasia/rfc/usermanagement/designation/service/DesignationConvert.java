package com.nicasia.rfc.usermanagement.designation.service;

import com.nicasia.rfc.usermanagement.designation.dto.DesignationResource;
import com.nicasia.rfc.usermanagement.designation.entity.Designation;

import java.util.List;

public interface DesignationConvert {

    DesignationResource convertDesignation(Designation designation);

    List<DesignationResource> convertAllDesignationResource(List<Designation> allDesignation);

}
