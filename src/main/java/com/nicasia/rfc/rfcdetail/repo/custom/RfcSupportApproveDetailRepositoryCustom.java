package com.nicasia.rfc.rfcdetail.repo.custom;

import com.nicasia.rfc.rfcdetail.entity.RfcDetail;
import com.nicasia.rfc.rfcdetail.entity.RfcSupportApproveDetail;
import com.nicasia.rfc.shared.abstracts.BaseRepositoryCustom;
import com.nicasia.rfc.util.PageResult;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RfcSupportApproveDetailRepositoryCustom extends BaseRepositoryCustom<RfcSupportApproveDetail> {

    List<RfcSupportApproveDetail> findAllByRfcId(Long rfcId);

    PageResult<RfcDetail> findAllByRequestedForType(String refCode,
                                                    String requestedFor,
                                                    Pageable pageable);

}
