package com.nicasia.rfc.usermanagement.designation.service;

import com.nicasia.rfc.exception.ClientException;
import com.nicasia.rfc.exception.ResourceNotAvailableException;
import com.nicasia.rfc.usermanagement.designation.dto.DesignationRequest;
import com.nicasia.rfc.usermanagement.designation.dto.DesignationResource;
import com.nicasia.rfc.usermanagement.designation.entity.Designation;
import com.nicasia.rfc.usermanagement.designation.repo.DesignationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DesignationServiceImpl implements DesignationService {

    private final DesignationRepository designationRepository;
    private final DesignationConvert designationConvert;

    public DesignationServiceImpl(DesignationRepository designationRepository, DesignationConvert designationConvert) {
        this.designationRepository = designationRepository;
        this.designationConvert = designationConvert;
    }

    @Override
    public DesignationResource findByDesignationName(String name) {
        final Designation name1 = (designationRepository.findByDesignationName(name)).orElseThrow(
                () -> new ResourceNotAvailableException("The designation you are trying to fine", "name", name));
        return designationConvert.convertDesignation(name1);
    }

//    @Override
//    public Designation findById(Long id) {
//        return designationRepository.findById(id).get();
//    }

    @Override
    public List<DesignationResource> findAllDesignation() {
        final Iterable<Designation> all = designationRepository.findAll();

        return designationConvert.convertAllDesignationResource((List<Designation>) all);
    }

    @Override
    public DesignationResource updateDesignation(Long id, DesignationRequest designationRequest) {

        final Designation byId = designationRepository.findById(id).orElseThrow
                (() -> new ResourceNotAvailableException("Designation", "code", designationRequest.getCode()));

        byId.setDesgname(designationRequest.getName());
        byId.setDesgcode(designationRequest.getCode());
        final Designation save = designationRepository.save(byId);
        return designationConvert.convertDesignation(save);

    }

    @Override
    public DesignationResource addNewDesignation(DesignationRequest designationRequest) {
        Optional<Designation> designation = designationRepository.findByDesignationCode(designationRequest.getCode());
        if (designation.isPresent()) {

            throw new ClientException("Designation already exists");
        }
        Designation designation1 = new Designation();
        designation1.setDesgname(designationRequest.getName());
        designation1.setDesgcode(designationRequest.getCode());
        Designation save = designationRepository.save(designation1);

        return designationConvert.convertDesignation(save);
    }

    @Override
    public void removeDesignation(DesignationRequest designationRequest) {
        Designation designation = designationRepository.findByDesignationCode(designationRequest.getCode())
                .orElseThrow
                        (() -> new ResourceNotAvailableException("Designation", "designation", designationRequest.getCode()));
        designationRepository.delete(designation);

    }

     @Override
    public Designation findById(Long id) {
        return designationRepository.findById(id).orElseThrow
                (() -> new ClientException("client not found"));

    }

    //this is comment
    //
}

