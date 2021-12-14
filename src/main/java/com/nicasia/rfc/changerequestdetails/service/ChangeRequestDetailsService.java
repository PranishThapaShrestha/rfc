package com.nicasia.rfc.changerequestdetails.service;

import com.nicasia.rfc.changerequestdetails.dto.ChangeRequestDetailsRequest;
import com.nicasia.rfc.changerequestdetails.dto.ChangeRequestDetailsResponse;
import com.nicasia.rfc.changerequestdetails.entity.ChangeRequestDetails;

import java.util.List;
import java.util.Optional;

public interface ChangeRequestDetailsService {

    ChangeRequestDetailsResponse createChangeReqDetails(Long id,ChangeRequestDetailsRequest changeRequestDetailsRequest);

    List<ChangeRequestDetailsResponse> getAllChangeReqDetails();

    Optional<ChangeRequestDetails> findById(Long id);

    ChangeRequestDetailsResponse updateChangeReqDetails(Long id,ChangeRequestDetailsRequest changeRequestDetailsRequest);
}
