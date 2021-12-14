package com.nicasia.rfc.changerequestidentification;

import com.nicasia.rfc.changerequestidentification.dto.ChangeReqIdentificationRequest;
import com.nicasia.rfc.changerequestidentification.dto.ChangeReqIdentificationResponse;
import com.nicasia.rfc.changerequestidentification.service.ChangeReqIdentificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/Cri")
public class ChangeReqIdentificationController {

    private ChangeReqIdentificationService changeReqIdentificationService;

    @PostMapping(value = "/{id}/createcri")
    public List<ChangeReqIdentificationResponse> addChangeReqIdentification(
            @PathVariable(value = "id") Long id, ChangeReqIdentificationRequest changeReqIdentificationRequest) {
        return changeReqIdentificationService
                .createCri(id, (List<ChangeReqIdentificationRequest>) changeReqIdentificationRequest);
    }

    @PostMapping(value = "/{id}/update")
    public ChangeReqIdentificationResponse updateChangeReqIdentification(
            @PathVariable(value = "id") Long id, ChangeReqIdentificationRequest changeReqIdentificationRequest) {
        return changeReqIdentificationService.updateCri(id, changeReqIdentificationRequest);
    }

    @GetMapping(value = "/all-changereqidentifications")
    public List<ChangeReqIdentificationResponse> getAllChangeReqIdentification() {
        return changeReqIdentificationService.getAllCri();
    }

}
