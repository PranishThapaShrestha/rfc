package com.nicasia.rfc.core.usermanagement.designation.service;

import com.nicasia.rfc.core.usermanagement.designation.entity.Designation;
import com.nicasia.rfc.core.usermanagement.designation.dto.DesignationResource;

import java.util.List;

public interface DesignationConvert {

    DesignationResource convertDesignation(Designation designation);

    List<DesignationResource> convertAllDesignationResource(List<Designation> allDesignation);

}
