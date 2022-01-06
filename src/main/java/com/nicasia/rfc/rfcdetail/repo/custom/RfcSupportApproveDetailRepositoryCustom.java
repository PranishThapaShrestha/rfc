package com.nicasia.rfc.rfcdetail.repo.custom;

import com.nicasia.rfc.rfcdetail.entity.RfcSupportApproveDetail;
import com.nicasia.rfc.shared.abstracts.BaseRepositoryCustom;

import java.util.List;

public interface RfcSupportApproveDetailRepositoryCustom extends BaseRepositoryCustom<RfcSupportApproveDetail> {

    List<RfcSupportApproveDetail> findAllByRfcId(Long rfcId);

}
