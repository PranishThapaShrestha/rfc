package com.nicasia.rfc.rfcdetail;

import com.nicasia.rfc.rfcdetail.dto.RfcDetailRequest;
import com.nicasia.rfc.rfcdetail.dto.RfcDetailResponse;
import com.nicasia.rfc.rfcdetail.service.RfcDetailService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/rfcdetails")
public class RfcDetailController {

    private final RfcDetailService rfcDetailService;

    public RfcDetailController(RfcDetailService rfcDetailService) {
        this.rfcDetailService = rfcDetailService;
    }

    @PostMapping(value = "/addrfc")
    public RfcDetailResponse createRfcDetails(@RequestBody RfcDetailRequest rfcDetailRequest){
       return rfcDetailService.createRfcDetail(rfcDetailRequest);
    }

    @PostMapping(value = "/{id}/update")
    public RfcDetailResponse updateRfcDetails(@PathVariable(value = "id")Long id,RfcDetailRequest rfcDetailRequest){
        return rfcDetailService.updateRfcDetail(id, rfcDetailRequest);
    }

    @PostMapping(value = "/{id}/remove")
    public RfcDetailResponse removeById(@PathVariable(value = "id")Long id){
        return rfcDetailService.removeRfcDetail(id);
    }


}
