package com.nicasia.rfc.rfcdetail.repo;

import com.nicasia.rfc.rfcdetail.entity.QRfcDetail;
import com.nicasia.rfc.rfcdetail.entity.RfcDetail;
import com.nicasia.rfc.shared.abstracts.BaseRepositoryImpl;

public class RfcDetailRepositoryImpl extends BaseRepositoryImpl<RfcDetail, RfcDetailRepository> {


    QRfcDetail qRfcDetail= QRfcDetail.rfcDetail;

    public RfcDetailRepositoryImpl(Class<?> domainClass) {
        super(RfcDetail.class);
    }

    public void setRepository(RfcDetailRepository rfcDetailRepository){
        this.repository=rfcDetailRepository;
    }



}
