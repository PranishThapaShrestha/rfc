package com.nicasia.rfc.rfcdetail;

import com.nicasia.rfc.rfcdetail.dto.RfcDetailRequest;
import com.nicasia.rfc.rfcdetail.dto.RfcDetailResponse;
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

    @PostMapping(value = "/rfcdetail")
    public SuccessResponse createRfcCreatePreApprovalRfcDetail(@RequestBody RfcDetailRequest rfcDetailRequest) {
        return rfcDetailService.createPreApprovalRfcDetail(rfcDetailRequest);
    }

    @GetMapping(value = "/{rfcdetail_id}")
    public RfcDetailResponse getAllPreApprovalRequest(@PathVariable Long rfcdetail_id) {
        return rfcDetailService.getPreApprovalRfcDetail(rfcdetail_id);
    }

    public PageResult<RfcDetailResponse> getPreApprovalRequest(@RequestParam (value = "ref_code",required = false)String refCode,
                                                               Pageable pageable){
        return null;
    }

}
