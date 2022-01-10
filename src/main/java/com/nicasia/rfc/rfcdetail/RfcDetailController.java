package com.nicasia.rfc.rfcdetail;

import com.nicasia.rfc.rfcdetail.dto.RfcDetailRequest;
import com.nicasia.rfc.rfcdetail.dto.RfcDetailResponse;
import com.nicasia.rfc.rfcdetail.dto.RfcPreapprovalResponse;
import com.nicasia.rfc.rfcdetail.service.RfcDetailService;
import com.nicasia.rfc.shared.succesresponse.SuccessResponse;
import com.nicasia.rfc.util.PageResult;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1")
public class RfcDetailController {

    private final RfcDetailService rfcDetailService;

    public RfcDetailController(RfcDetailService rfcDetailService) {
        this.rfcDetailService = rfcDetailService;
    }

    @PostMapping(value = "/pre_approval")
    public SuccessResponse createRfcCreatePreApprovalRfcDetail(@RequestBody RfcDetailRequest rfcDetailRequest) {
        return rfcDetailService.createPreApprovalRfcDetail(rfcDetailRequest);
    }

    @GetMapping(value = "/pre_approval")
    public PageResult<RfcPreapprovalResponse> getPreApprovalRequest(@RequestParam(value = "ref_code", required = false) String refCode,
                                                                    Pageable pageable) {
        return rfcDetailService.getPreApprovalRfc(refCode, pageable);
    }


    @GetMapping(value = "/{rfcdetail_id}")
    public RfcDetailResponse getAllPreApprovalRequest(@PathVariable Long rfcdetail_id) {
        return rfcDetailService.getPreApprovalRfcDetail(rfcdetail_id);
    }

    @GetMapping(value = "/pre_approval/{requested_for}")
    public PageResult<RfcPreapprovalResponse> getAllToBeSupportedRequests(@RequestParam(value = "ref_code", required = false) String refCode,
                                                                          @PathVariable(value = "requested_for") String requestedFor, Pageable pageable) {

        return rfcDetailService.getAllRequestedForPreApprovalDetails(refCode,requestedFor,pageable);
    }


}
