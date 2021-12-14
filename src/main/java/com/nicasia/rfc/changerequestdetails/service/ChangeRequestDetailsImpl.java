package com.nicasia.rfc.changerequestdetails.service;

import com.nicasia.rfc.changerequestdetails.dto.ChangeRequestDetailsRequest;
import com.nicasia.rfc.changerequestdetails.dto.ChangeRequestDetailsResponse;
import com.nicasia.rfc.changerequestdetails.entity.ChangeRequestDetails;
import com.nicasia.rfc.changerequestdetails.repo.ChangeRequestDetailsRepository;
import com.nicasia.rfc.rfcdetail.entity.RfcDetail;
import com.nicasia.rfc.rfcdetail.service.RfcDetailService;
import com.nicasia.rfc.shared.enums.Status;
import com.nicasia.rfc.shared.exception.ResourceNotAvailableException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChangeRequestDetailsImpl implements ChangeRequestDetailsService {

    private final ChangeRequestDetailsRepository changeRequestDetailsRepository;
    private final RfcDetailService rfcDetailService;
    private final ChangeRequestDetailsConvert changeRequestDetailsConvert;

    public ChangeRequestDetailsImpl(ChangeRequestDetailsRepository changeRequestDetailsRepository, RfcDetailService rfcDetailService, ChangeRequestDetailsConvert changeRequestDetailsConvert) {
        this.changeRequestDetailsRepository = changeRequestDetailsRepository;
        this.rfcDetailService = rfcDetailService;
        this.changeRequestDetailsConvert = changeRequestDetailsConvert;
    }

    @Override
    public ChangeRequestDetailsResponse createChangeReqDetails(Long id, ChangeRequestDetailsRequest changeRequestDetailsRequest) {
        RfcDetail rfcDetail = rfcDetailService.findById(id);
        ChangeRequestDetails changeRequestDetails = null;
        if (rfcDetail != null) {
            ChangeRequestDetails changeRequestDetails1 = new ChangeRequestDetails();
            changeRequestDetails1.setDescription(changeRequestDetailsRequest.getDescription());
            changeRequestDetails1.setRfcDetail(rfcDetail);
            changeRequestDetails1.setJustification(changeRequestDetailsRequest.getJustification());
            changeRequestDetails1.setPriority(changeRequestDetailsRequest.getPriority());
            changeRequestDetails1.setStatus(Status.ACTIVE);
            assert changeRequestDetails != null;
            changeRequestDetails = changeRequestDetailsRepository.save(changeRequestDetails1);
        }
        return changeRequestDetailsConvert.convertOne(changeRequestDetails);
    }

    @Override
    public List<ChangeRequestDetailsResponse> getAllChangeReqDetails() {

        final Iterable<ChangeRequestDetails> all = changeRequestDetailsRepository.findAll();
        return changeRequestDetailsConvert.convertAll((List<ChangeRequestDetails>) all);
    }

    @Override
    public ChangeRequestDetailsResponse updateChangeReqDetails(Long id, ChangeRequestDetailsRequest changeRequestDetailsRequest) {
        ChangeRequestDetails changeRequestDetails =changeRequestDetailsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotAvailableException("ChangeRequestDetailsResponse","id",findById(id)));
        changeRequestDetails.setDescription(changeRequestDetailsRequest.getDescription());
        changeRequestDetails.setJustification(changeRequestDetailsRequest.getJustification());
        changeRequestDetailsRepository.save(changeRequestDetails);

        return changeRequestDetailsConvert.convertOne(changeRequestDetailsRepository.save(changeRequestDetails));
    }




    @Override
    public Optional<ChangeRequestDetails> findById(Long id) {
        return changeRequestDetailsRepository.findById(id);

    }
}
