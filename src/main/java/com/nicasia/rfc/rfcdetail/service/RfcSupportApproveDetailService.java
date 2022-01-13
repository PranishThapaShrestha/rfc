package com.nicasia.rfc.rfcdetail.service;

import com.nicasia.rfc.rfcdetail.entity.RequestType;
import com.nicasia.rfc.rfcdetail.entity.RfcDetail;
import com.nicasia.rfc.rfcdetail.entity.RfcSupportApproveDetail;
import com.nicasia.rfc.util.PageResult;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RfcSupportApproveDetailService {

    void saveSupportApproveDetails(List<Long> approvedByUserIds,
                                   List<Long> supportedByUserIds,
                                   RfcDetail rfcDetail, RequestType requestType
                                   );

    List<RfcSupportApproveDetail> findAllRfcSupportApproveDetailByRfcId(Long rfcDetailId);

    PageResult<RfcDetail> findAllExpenditureDetailsWithRequestedForType(String refCode,
                                                                        String requestedFor,
                                                                        Pageable pageable);
    void saveDetail(RfcSupportApproveDetail rfcSupportApproveDetail);
}
