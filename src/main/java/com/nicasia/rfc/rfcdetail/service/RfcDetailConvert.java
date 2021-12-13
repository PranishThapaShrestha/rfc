package com.nicasia.rfc.rfcdetail.service;

import com.nicasia.rfc.rfcdetail.dto.RfcDetailResponse;
import com.nicasia.rfc.rfcdetail.entity.RfcDetail;

import java.util.List;

public interface RfcDetailConvert {

    RfcDetailResponse convertOne(RfcDetail rfcDetail);

    List<RfcDetailResponse> convertAll(List<RfcDetail> rfcDetails);
}
