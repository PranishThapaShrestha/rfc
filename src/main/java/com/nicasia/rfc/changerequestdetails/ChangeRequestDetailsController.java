package com.nicasia.rfc.changerequestdetails;

import com.nicasia.rfc.changerequestdetails.dto.ChangeRequestDetailsRequest;
import com.nicasia.rfc.changerequestdetails.dto.ChangeRequestDetailsResponse;
import com.nicasia.rfc.changerequestdetails.service.ChangeRequestDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class ChangeRequestDetailsController {

    private final ChangeRequestDetailsService changeRequestDetailsService;

    public ChangeRequestDetailsController(ChangeRequestDetailsService changeRequestDetailsService) {
        this.changeRequestDetailsService = changeRequestDetailsService;
    }

    @PostMapping(value = "/rfcdetails/{id}/changerequestdetails")
    public ChangeRequestDetailsResponse createChangeReqDetails(
            @PathVariable(value = "id") Long id,
            @RequestBody ChangeRequestDetailsRequest changeRequestDetailsRequest) {
        return changeRequestDetailsService.createChangeReqDetails(id, changeRequestDetailsRequest);
    }

    @GetMapping(value = "/changerequestdetails")
    public List<ChangeRequestDetailsResponse> fetchAllChangeReqDetails() {
        return changeRequestDetailsService.getAllChangeReqDetails();
    }

    @PostMapping(value = "/changerequestdetails/{id}/update")
    public ChangeRequestDetailsResponse updateChangeReqDetails(
            @PathVariable(value = "id") Long id,
            @RequestBody ChangeRequestDetailsRequest changeRequestDetailsRequest) {
        return changeRequestDetailsService.updateChangeReqDetails(id, changeRequestDetailsRequest);
    }


}
