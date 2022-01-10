package com.nicasia.rfc.rfcdetail.service.Impl;

import com.nicasia.rfc.core.usermanagement.user.dto.UserMiniResource;
import com.nicasia.rfc.rfcdetail.dto.RfcDetailResponse;
import com.nicasia.rfc.rfcdetail.dto.RfcPreapprovalResponse;
import com.nicasia.rfc.rfcdetail.dto.SupportedApprovedDetail;
import com.nicasia.rfc.rfcdetail.entity.RfcDetail;
import com.nicasia.rfc.rfcdetail.entity.RfcSupportApproveDetail;
import com.nicasia.rfc.rfcdetail.service.RfcDetailConvert;
import com.nicasia.rfc.shared.enums.Status;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RfcDetailConvertImpl implements RfcDetailConvert {

    @Override
    public RfcDetailResponse convertToRfcDetail(List<RfcSupportApproveDetail> rfcSupportApproveDetails,
                                                RfcDetail rfcDetail,
                                                Map<Long, UserMiniResource> userMiniResourceMap) {

        return RfcDetailResponse.builder()
                .projectname(rfcDetail.getProjectname())
                .requestdate(rfcDetail.getRequestdate())
                .supportedApprovedDetails(rfcSupportApproveDetails.stream()
                        .map(rfcSupportApproveDetail -> convertToSupportApproveDetail(rfcSupportApproveDetail, userMiniResourceMap))
                        .collect(Collectors.toList()))
                .unit(rfcDetail.getUnit()).build();

    }

    @Override
    public List<RfcPreapprovalResponse> convertAllToCurrentRfcStatus(List<RfcDetail> rfcDetails)  {
        return rfcDetails.stream().map(rfcDetail -> convertToCurrentRfcStatus(rfcDetail)).collect(Collectors.toList());

    }

    private RfcPreapprovalResponse convertToCurrentRfcStatus(RfcDetail rfcDetail) {

        return RfcPreapprovalResponse.builder()
                .currentApprovalStatus(rfcDetail.getRfcApprovalStatus().name())
                .projectName(rfcDetail.getProjectname())
                .requestedDate(rfcDetail.getRequestdate())
                .status(Status.ACTIVE)
                .build();
    }

    private SupportedApprovedDetail convertToSupportApproveDetail(RfcSupportApproveDetail rfcSupportApproveDetail,
                                                                  Map<Long, UserMiniResource> longUserMiniResourceMap) {
        return SupportedApprovedDetail.builder()
                .approveSupportId(rfcSupportApproveDetail.getId())
                .userMiniResource(longUserMiniResourceMap.get(rfcSupportApproveDetail.getUser().getId()))
                .status(rfcSupportApproveDetail.getStatus().name())
                .requestedFor(rfcSupportApproveDetail.getRequestedForType().name())
                .type(rfcSupportApproveDetail.getRequestType().name())
                .build();
    }
}

