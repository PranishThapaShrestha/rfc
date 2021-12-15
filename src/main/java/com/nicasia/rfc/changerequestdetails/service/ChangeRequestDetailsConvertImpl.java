package com.nicasia.rfc.changerequestdetails.service;

import com.nicasia.rfc.changerequestdetails.dto.ChangeRequestDetailsResponse;
import com.nicasia.rfc.changerequestdetails.entity.ChangeRequestDetails;
import com.nicasia.rfc.shared.enums.Status;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChangeRequestDetailsConvertImpl implements ChangeRequestDetailsConvert {

    @Override
    public ChangeRequestDetailsResponse convertOne(ChangeRequestDetails changeRequestDetails) {
        return convertHelper(changeRequestDetails);
    }

//    @Override
//    public List<ChangeRequestDetailsResponse> convertAll(ChangeRequestDetails changeRequestDetails) {
//        return changeRequestDetails.stream()
//                .map(changeRequestDetails1 -> convertHelper(changeRequestDetails1)).collect(Collectors.toList());return null;
//    }

    @Override
    public List<ChangeRequestDetailsResponse> convertAll(List<ChangeRequestDetails> changeRequestDetails) {
        return changeRequestDetails.stream()
                .map(changeRequestDetails1 -> convertHelper(changeRequestDetails1)).collect(Collectors.toList());
    }

    private ChangeRequestDetailsResponse convertHelper(ChangeRequestDetails changeRequestDetails) {
        return ChangeRequestDetailsResponse.builder()
                .id(changeRequestDetails.getRfcDetail().getId())
                .description(changeRequestDetails.getDescription())
                .justification(changeRequestDetails.getJustification())
                .status(Status.ACTIVE.name())
                .priority(changeRequestDetails.getPriority().name())
                .build();
    }
}
