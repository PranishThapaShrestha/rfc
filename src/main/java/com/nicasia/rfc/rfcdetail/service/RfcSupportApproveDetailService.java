package com.nicasia.rfc.rfcdetail.service;

import com.nicasia.rfc.rfcdetail.entity.RequestedForType;
import com.nicasia.rfc.rfcdetail.entity.RfcDetail;

import java.util.List;

public interface RfcSupportApproveDetailService {

    void saveSupportApproveDetails(List<Long> approvedByUserIds,
                                   List<Long> supportedByUserIds,
                                   RfcDetail rfcDetail,
                                   RequestedForType requestedForType
                                   );


}
