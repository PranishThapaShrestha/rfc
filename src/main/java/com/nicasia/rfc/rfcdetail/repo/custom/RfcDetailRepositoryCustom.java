package com.nicasia.rfc.rfcdetail.repo.custom;

import com.nicasia.rfc.rfcdetail.entity.RfcDetail;
import com.nicasia.rfc.shared.abstracts.BaseRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RfcDetailRepositoryCustom extends BaseRepositoryCustom<RfcDetail> {


    Page<RfcDetail> findAllRequestedRfcDetails(String refCode,Pageable pageable);

}
