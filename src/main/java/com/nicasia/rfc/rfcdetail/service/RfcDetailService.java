package com.nicasia.rfc.rfcdetail.service;

import com.nicasia.rfc.rfcdetail.dto.RfcDetailRequest;
import com.nicasia.rfc.rfcdetail.entity.RfcDetail;

public interface RfcDetailService {

    RfcDetail createRfcDetail(RfcDetailRequest rfcDetailRequest);

    RfcDetail updateRfcDetail(Long id, RfcDetailRequest rfcDetailRequest);

    RfcDetail removeRfcDetail(Long id);

}
