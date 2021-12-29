package com.nicasia.rfc.rfcdetail.repo.Impl;

import com.nicasia.rfc.rfcdetail.entity.QRfcDetail;
import com.nicasia.rfc.rfcdetail.entity.RfcDetail;
import com.nicasia.rfc.rfcdetail.repo.RfcDetailRepository;
import com.nicasia.rfc.rfcdetail.repo.custom.RfcDetailRepositoryCustom;
import com.nicasia.rfc.shared.abstracts.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

public class RfcDetailRepositoryImpl extends BaseRepositoryImpl<RfcDetail, RfcDetailRepository>
        implements RfcDetailRepositoryCustom {


    QRfcDetail qRfcDetail = QRfcDetail.rfcDetail;

    public RfcDetailRepositoryImpl() {
        super(RfcDetail.class);
    }

    @Lazy
    @Autowired
    public void setRepository(RfcDetailRepository rfcDetailRepository) {
        this.repository = rfcDetailRepository;
    }


}
