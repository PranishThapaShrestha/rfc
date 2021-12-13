package com.nicasia.rfc.changerequestidentification.service;

import com.nicasia.rfc.changerequestidentification.dto.ChangeReqIdentificationResponse;
import com.nicasia.rfc.changerequestidentification.entity.ChangeRequestIdentification;

import java.util.List;

public interface ChangeRequestIdentificationConvert {

    ChangeReqIdentificationResponse convertOne(ChangeRequestIdentification changeRequestIdentification);

    List<ChangeReqIdentificationResponse> convertAll(List<ChangeRequestIdentification> changeRequestIdentifications);

}
