package com.nicasia.rfc.rfcdetail.service;

import com.nicasia.rfc.rfcdetail.entity.RequestType;
import com.nicasia.rfc.rfcdetail.entity.RfcDetail;
import com.nicasia.rfc.rfcdetail.entity.RfcSupportApproveDetail;

import java.util.List;

public interface RfcSupportApproveDetailService {

    void saveSupportApproveDetails(List<Long> approvedByUserIds,
                                   List<Long> supportedByUserIds,
                                   RfcDetail rfcDetail, RequestType requestType
                                   );

    List<RfcSupportApproveDetail> findRfcSupportApproveDetailById(Long rfcDetailId);

//    void forward(List<Long> approversIds,List<Long> supportersIds,RfcDetail rfcDetail,RequestType requestType);

}
