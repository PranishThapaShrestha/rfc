package com.nicasia.rfc.changerequestdetails.service;

import com.nicasia.rfc.changerequestdetails.dto.ChangeRequestDetailsResponse;
import com.nicasia.rfc.changerequestdetails.entity.ChangeRequestDetails;

import java.util.List;

public interface ChangeRequestDetailsConvert {

    ChangeRequestDetailsResponse convertOne(ChangeRequestDetails changeRequestDetails);

    List<ChangeRequestDetailsResponse> convertAll(List<ChangeRequestDetails> changeRequestDetails);

}
