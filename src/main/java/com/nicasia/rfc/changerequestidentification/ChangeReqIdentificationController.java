package com.nicasia.rfc.changerequestidentification;

import com.nicasia.rfc.changerequestidentification.dto.ChangeReqIdentificationRequest;
import com.nicasia.rfc.changerequestidentification.dto.ChangeReqIdentificationResponse;
import com.nicasia.rfc.changerequestidentification.service.ChangeReqIdentificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class ChangeReqIdentificationController {

    private ChangeReqIdentificationService changeReqIdentificationService;

    @PostMapping(value = "/rfcdetails/{id}/changerequestidentifications")
    public List<ChangeReqIdentificationResponse> addChangeReqIdentification(
            @PathVariable(value = "id") Long id, ChangeReqIdentificationRequest changeReqIdentificationRequest) {
        return changeReqIdentificationService
                .createCri(id, (List<ChangeReqIdentificationRequest>) changeReqIdentificationRequest);
    }

    @PostMapping(value = "/changerequestidentifications/{id}/update")
    public ChangeReqIdentificationResponse updateChangeReqIdentification(
            @PathVariable(value = "id") Long id, ChangeReqIdentificationRequest changeReqIdentificationRequest) {
        return changeReqIdentificationService.updateCri(id, changeReqIdentificationRequest);
    }

    @GetMapping(value = "/changerequestidentifications")
    public List<ChangeReqIdentificationResponse> getAllChangeReqIdentification() {
        return changeReqIdentificationService.getAllCri();
    }


}
