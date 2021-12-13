package com.nicasia.rfc.rfcdetail.service;

import com.nicasia.rfc.rfcdetail.dto.RfcDetailResponse;
import com.nicasia.rfc.rfcdetail.entity.RfcDetail;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RfcDetailConvertImpl implements RfcDetailConvert {

    @Override
    public RfcDetailResponse convertOne(RfcDetail rfcDetail) {
        return convertHelper(rfcDetail);
    }

    @Override
    public List<RfcDetailResponse> convertAll(List<RfcDetail> rfcDetails) {
        return rfcDetails.stream()
                .map(rfcDetail ->convertHelper(rfcDetail)).collect(Collectors.toList());
    }


    public RfcDetailResponse convertHelper(RfcDetail rfcDetail) {
        return RfcDetailResponse.builder()
                .deparmentname(rfcDetail.getProjectname())
                .requestedby(rfcDetail.getRequestedby().getName())
                .requestdate(rfcDetail.getRequestdate())
                .approvalStatus(rfcDetail.getApprovalStatus().name())
                .datedecided(rfcDetail.getDatedecided())
                .status(rfcDetail.getStatus().name())
                .supportedby(rfcDetail.getSupportedby().getName())
                .projectname(rfcDetail.getProjectname())
                .unit(rfcDetail.getUnit())
                .build();

    }
}
