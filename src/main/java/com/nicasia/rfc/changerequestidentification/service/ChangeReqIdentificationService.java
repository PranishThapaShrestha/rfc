package com.nicasia.rfc.changerequestidentification.service;

import com.nicasia.rfc.changerequestidentification.dto.ChangeReqIdentificationRequest;
import com.nicasia.rfc.changerequestidentification.dto.ChangeReqIdentificationResponse;

import java.util.List;

public interface ChangeReqIdentificationService {

    List<ChangeReqIdentificationResponse> doChangeRequestIdentification(Long id,List<ChangeReqIdentificationRequest> changeReqIdentificationRequests);

    ChangeReqIdentificationResponse updateCri(Long id,ChangeReqIdentificationRequest changeReqIdentificationRequest);

    List<ChangeReqIdentificationResponse> getAllCri();



}
