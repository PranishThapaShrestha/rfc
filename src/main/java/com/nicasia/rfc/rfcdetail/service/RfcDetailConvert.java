package com.nicasia.rfc.rfcdetail.service;

import com.nicasia.rfc.core.usermanagement.user.dto.UserMiniResource;
import com.nicasia.rfc.rfcdetail.dto.RfcDetailResponse;
import com.nicasia.rfc.rfcdetail.dto.RfcPreapprovalResponse;
import com.nicasia.rfc.rfcdetail.entity.RfcDetail;
import com.nicasia.rfc.rfcdetail.entity.RfcSupportApproveDetail;

import java.util.List;
import java.util.Map;

public interface RfcDetailConvert {

    RfcDetailResponse convertToRfcDetail(List<RfcSupportApproveDetail> rfcSupportApproveDetails,
                                         RfcDetail rfcDetail,
                                         Map<Long, UserMiniResource> userMiniResourceMap);

    List<RfcPreapprovalResponse> convertAllToCurrentRfcStatus(List<RfcDetail> rfcDetails);


}
