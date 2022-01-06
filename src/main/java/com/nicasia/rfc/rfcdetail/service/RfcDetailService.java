package com.nicasia.rfc.rfcdetail.service;

import com.nicasia.rfc.rfcdetail.dto.RfcDetailRequest;
import com.nicasia.rfc.rfcdetail.dto.RfcDetailResponse;
import com.nicasia.rfc.rfcdetail.entity.RfcDetail;
import com.nicasia.rfc.shared.succesresponse.SuccessResponse;

public interface RfcDetailService {

    SuccessResponse createPreApprovalRfcDetail(RfcDetailRequest rfcDetailRequest);

    RfcDetailResponse getPreApprovalRfcDetail(Long rfcDetailId);


    RfcDetail findById(Long id);

//    SuccessResponse addRemarks(AddRemarksDto addRemarksDto,Long rfcDetailsId);
}
