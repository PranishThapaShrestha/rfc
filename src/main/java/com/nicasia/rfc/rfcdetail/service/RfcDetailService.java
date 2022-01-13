package com.nicasia.rfc.rfcdetail.service;

import com.nicasia.rfc.rfcdetail.dto.PutRemarksDto;
import com.nicasia.rfc.rfcdetail.dto.RfcDetailRequest;
import com.nicasia.rfc.rfcdetail.dto.RfcDetailResponse;
import com.nicasia.rfc.rfcdetail.dto.RfcPreapprovalResponse;
import com.nicasia.rfc.rfcdetail.entity.RfcDetail;
import com.nicasia.rfc.shared.succesresponse.SuccessResponse;
import com.nicasia.rfc.util.PageResult;
import org.springframework.data.domain.Pageable;

public interface RfcDetailService {

    SuccessResponse createPreApprovalRfcDetail(RfcDetailRequest rfcDetailRequest);

    RfcDetailResponse getPreApprovalRfcDetail(Long rfcDetailId);

    PageResult<RfcPreapprovalResponse> getPreApprovalRfc(String refCode, Pageable pageable);

    RfcDetail findById(Long id);

    PageResult<RfcPreapprovalResponse> getAllRequestedForPreApprovalDetails(String refCode,
                                                                            String requestedFor,
                                                                            Pageable pageable);


    SuccessResponse putRemarks(PutRemarksDto putRemarksDto, Long rfcDetailId);

}
