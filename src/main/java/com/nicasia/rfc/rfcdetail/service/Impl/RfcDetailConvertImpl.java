package com.nicasia.rfc.rfcdetail.service.Impl;

import com.nicasia.rfc.core.usermanagement.user.dto.UserMiniResource;
import com.nicasia.rfc.rfcdetail.dto.RfcDetailResponse;
import com.nicasia.rfc.rfcdetail.dto.SupportedApprovedDetail;
import com.nicasia.rfc.rfcdetail.entity.RfcDetail;
import com.nicasia.rfc.rfcdetail.entity.RfcSupportApproveDetail;
import com.nicasia.rfc.rfcdetail.service.RfcDetailConvert;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RfcDetailConvertImpl implements RfcDetailConvert {

    @Override
    public RfcDetailResponse convertToRfcDetail(List<RfcSupportApproveDetail> rfcSupportApproveDetails, RfcDetail rfcDetail, Map<Long, UserMiniResource> userMiniResourceMap) {

        return  RfcDetailResponse.builder()
                .projectname(rfcDetail.getProjectname())
                .unit(rfcDetail.getUnit())
                .requestdate(rfcDetail.getRequestdate())
                .supportedApprovedDetails(rfcSupportApproveDetails.stream()
                        .map(rfcSupportApproveDetail -> convertToSupportApproveDetail
                                (rfcSupportApproveDetail,userMiniResourceMap)).collect(Collectors.toList()))
                .build();
    }

    private SupportedApprovedDetail convertToSupportApproveDetail(RfcSupportApproveDetail rfcSupportApproveDetail, Map<Long, UserMiniResource> userMiniResourceMap) {

        return SupportedApprovedDetail.builder()
                .approveSupportId(rfcSupportApproveDetail.getId())
                .requestedFor(rfcSupportApproveDetail.getRequestedForType().name())
                .userMiniResource(userMiniResourceMap.get(rfcSupportApproveDetail.getUser().getId()))
                .status(rfcSupportApproveDetail.getStatus().name())
                .type(rfcSupportApproveDetail.getRequestType().name()).build();

    }
}

