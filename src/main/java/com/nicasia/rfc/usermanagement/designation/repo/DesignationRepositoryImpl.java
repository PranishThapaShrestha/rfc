package com.nicasia.rfc.usermanagement.designation.repo;

import com.nicasia.rfc.abstracts.BaseRepositoryImpl;
import com.nicasia.rfc.usermanagement.designation.entity.Designation;
import com.nicasia.rfc.usermanagement.designation.entity.QDesignation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.Optional;

public class DesignationRepositoryImpl extends BaseRepositoryImpl<Designation, DesignationRepository>
        implements DesignationRepositoryCustom {

    QDesignation designation = QDesignation.designation;


    public DesignationRepositoryImpl() {
        super(Designation.class);
    }

    @Lazy
    @Autowired
    public void setRepository(DesignationRepository designationRepository) {
        this.repository = designationRepository;
    }

    @Override
    public Optional<Designation> findByDesignationCode(String code) {
        return repository.findOne(designation.desgcode.eq(code));

    }

    @Override
    public Optional<Designation> findByDesignationName(String name) {
        return repository.findOne(designation.desgname.eq(name));

    }
}
