package com.nicasia.rfc.rfcdetail.service;

import com.nicasia.rfc.rfcdetail.dto.RfcDetailRequest;
import com.nicasia.rfc.rfcdetail.dto.RfcDetailResponse;
import com.nicasia.rfc.rfcdetail.entity.RfcDetail;
import com.nicasia.rfc.shared.succesresponse.SuccessResponse;

import java.util.List;

public interface RfcDetailService {

    SuccessResponse createPreApprovalRfcDetail(RfcDetailRequest rfcDetailRequest);

    RfcDetailResponse updateRfcDetail(Long id, RfcDetailRequest rfcDetailRequest);

    RfcDetailResponse removeRfcDetail(Long id);

    List<RfcDetailResponse> retrieveAllRfcDetails();

    RfcDetail findById(Long id);

}
