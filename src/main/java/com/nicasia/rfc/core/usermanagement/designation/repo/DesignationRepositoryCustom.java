package com.nicasia.rfc.core.usermanagement.designation.repo;

import com.nicasia.rfc.core.usermanagement.designation.entity.Designation;
import com.nicasia.rfc.shared.abstracts.BaseRepositoryCustom;

import java.util.Optional;

public interface DesignationRepositoryCustom extends BaseRepositoryCustom<Designation> {

    Optional<Designation> findByDesignationCode(String code);

    Optional<Designation> findByDesignationName(String name);

    //Designation findById(Long id);


}
