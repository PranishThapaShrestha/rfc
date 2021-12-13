package com.nicasia.rfc.rfcdetail.service;

import com.nicasia.rfc.rfcdetail.dto.RfcDetailRequest;
import com.nicasia.rfc.rfcdetail.dto.RfcDetailResponse;
import com.nicasia.rfc.rfcdetail.entity.RfcDetail;

import java.util.List;

public interface RfcDetailService {

    RfcDetailResponse createRfcDetail(RfcDetailRequest rfcDetailRequest);

    RfcDetailResponse updateRfcDetail(Long id, RfcDetailRequest rfcDetailRequest);

    RfcDetailResponse removeRfcDetail(Long id);

    List<RfcDetailResponse> retrieveAllRfcDetails();

    RfcDetail findById(Long id);

}
