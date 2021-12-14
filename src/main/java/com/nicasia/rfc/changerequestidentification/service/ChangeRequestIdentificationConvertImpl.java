package com.nicasia.rfc.changerequestidentification.service;

import com.nicasia.rfc.changerequestidentification.dto.ChangeReqIdentificationResponse;
import com.nicasia.rfc.changerequestidentification.entity.ChangeRequestIdentification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChangeRequestIdentificationConvertImpl implements ChangeRequestIdentificationConvert {
    @Override
    public ChangeReqIdentificationResponse convertOne(ChangeRequestIdentification changeRequestIdentification) {
        return convert(changeRequestIdentification);
    }

    @Override
    public List<ChangeReqIdentificationResponse> convertAll(List<ChangeRequestIdentification> changeRequestIdentifications) {
        return changeRequestIdentifications.stream()
                .map(changeRequestIdentification -> convert(changeRequestIdentification))
                .collect(Collectors.toList());
    }

    private ChangeReqIdentificationResponse convert(ChangeRequestIdentification changeRequestIdentification) {
        return
                ChangeReqIdentificationResponse.builder()
                        .changeRequest(changeRequestIdentification.getRfcdetail().getId())
                        .changeTitle(changeRequestIdentification.getChangerequestidentification())
                        .build();
    }
}
