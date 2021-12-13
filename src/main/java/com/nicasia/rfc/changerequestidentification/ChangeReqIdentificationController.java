package com.nicasia.rfc.changerequestidentification;

import com.nicasia.rfc.changerequestidentification.dto.ChangeReqIdentificationRequest;
import com.nicasia.rfc.changerequestidentification.dto.ChangeReqIdentificationResponse;
import com.nicasia.rfc.changerequestidentification.service.ChangeReqIdentificationService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/Cri")
public class ChangeReqIdentificationController {

    private ChangeReqIdentificationService changeReqIdentificationService;

    @PostMapping(value = "/{id}/createcri")
    public List<ChangeReqIdentificationResponse> addChangeReqIdentification(
            @PathVariable(value = "id") Long id, ChangeReqIdentificationRequest changeReqIdentificationRequest) {
        return changeReqIdentificationService.createCri(id, (List<ChangeReqIdentificationRequest>) changeReqIdentificationRequest);
    }

}
