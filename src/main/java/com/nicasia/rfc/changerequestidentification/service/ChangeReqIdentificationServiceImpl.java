package com.nicasia.rfc.changerequestidentification.service;

import com.nicasia.rfc.changerequestidentification.dto.ChangeReqIdentificationRequest;
import com.nicasia.rfc.changerequestidentification.dto.ChangeReqIdentificationResponse;
import com.nicasia.rfc.changerequestidentification.entity.ChangeRequestIdentification;
import com.nicasia.rfc.changerequestidentification.repo.ChangeRequestIdentificationRepository;
import com.nicasia.rfc.rfcdetail.entity.RfcDetail;
import com.nicasia.rfc.rfcdetail.service.RfcDetailService;
import com.nicasia.rfc.shared.enums.Status;
import com.nicasia.rfc.shared.exception.ResourceNotAvailableException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ChangeReqIdentificationServiceImpl implements ChangeReqIdentificationService {

    private final RfcDetailService rfcDetailService;
    private final ChangeRequestIdentificationRepository changeRequestIdentificationRepository;
    private final ChangeRequestIdentificationConvert changeRequestIdentificationConvert;

    public ChangeReqIdentificationServiceImpl(RfcDetailService rfcDetailService,
                                              ChangeRequestIdentificationRepository changeRequestIdentificationRepository,
                                              ChangeRequestIdentificationConvert changeRequestIdentificationConvert) {
        this.rfcDetailService = rfcDetailService;
        this.changeRequestIdentificationRepository = changeRequestIdentificationRepository;
        this.changeRequestIdentificationConvert = changeRequestIdentificationConvert;
    }

    @Override
    public List<ChangeReqIdentificationResponse> createCri(Long id, List<ChangeReqIdentificationRequest> changeReqIdentificationRequests) {
        RfcDetail rfcDetail = rfcDetailService.findById(id);
        List<ChangeRequestIdentification> changeRequestIdentifications = null;
        if (rfcDetail != null) {

            List<ChangeRequestIdentification> changeRequestIdentification1 = new ArrayList<>();
            for (ChangeReqIdentificationRequest changeReqIdentificationRequest : changeReqIdentificationRequests) {
                ChangeRequestIdentification changeRequestIdentification = new ChangeRequestIdentification();
                changeRequestIdentification.setRfcdetail(rfcDetail);
                changeRequestIdentification.setChangerequestidentification(changeReqIdentificationRequest.getChangetitle());
                changeRequestIdentification.setStatus(Status.ACTIVE);
                changeRequestIdentification1.add(changeRequestIdentification);
            }

            changeRequestIdentifications = (List<ChangeRequestIdentification>) changeRequestIdentificationRepository.saveAll(changeRequestIdentification1);
        }
        return changeRequestIdentificationConvert.convertAll(changeRequestIdentifications);
    }

    @Override
    public ChangeReqIdentificationResponse updateCri(Long id, ChangeReqIdentificationRequest changeReqIdentificationRequest) {
        ChangeRequestIdentification changeRequestIdentification = changeRequestIdentificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotAvailableException("Request Identification", "id", id));
        changeRequestIdentification.setChangerequestidentification(changeReqIdentificationRequest.getChangetitle());

        return changeRequestIdentificationConvert.convertOne(changeRequestIdentificationRepository
                .save(changeRequestIdentification));
    }

    @Override
    public List<ChangeReqIdentificationResponse> getAllCri() {
        final Iterable<ChangeRequestIdentification> all = changeRequestIdentificationRepository.findAll();
        return changeRequestIdentificationConvert.convertAll((List<ChangeRequestIdentification>) all);
    }
}
