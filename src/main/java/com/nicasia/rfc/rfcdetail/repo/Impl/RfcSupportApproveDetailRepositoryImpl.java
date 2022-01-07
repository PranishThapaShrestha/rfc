package com.nicasia.rfc.rfcdetail.repo.Impl;

import com.nicasia.rfc.rfcdetail.entity.QRfcSupportApproveDetail;
import com.nicasia.rfc.rfcdetail.entity.RfcSupportApproveDetail;
import com.nicasia.rfc.rfcdetail.repo.RfcSupportApproveDetailRepository;
import com.nicasia.rfc.rfcdetail.repo.custom.RfcSupportApproveDetailRepositoryCustom;
import com.nicasia.rfc.shared.abstracts.BaseRepositoryImpl;
import com.nicasia.rfc.shared.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.List;

public class RfcSupportApproveDetailRepositoryImpl extends BaseRepositoryImpl<RfcSupportApproveDetail, RfcSupportApproveDetailRepository> implements RfcSupportApproveDetailRepositoryCustom {

    QRfcSupportApproveDetail qRfcSupportApproveDetail = QRfcSupportApproveDetail.rfcSupportApproveDetail;

    public RfcSupportApproveDetailRepositoryImpl() {
        super(RfcSupportApproveDetail.class);
    }

    @Lazy
    @Autowired
    public void setRepository(RfcSupportApproveDetailRepository rfcSupportApproveDetailRepository){
        this.repository=rfcSupportApproveDetailRepository;
    }

    @Override
    public List<RfcSupportApproveDetail> findAllByRfcId(Long rfcId) {

        return (List<RfcSupportApproveDetail>) repository.findAll(qRfcSupportApproveDetail.rfcDetail.id.eq(rfcId)
                .and(qRfcSupportApproveDetail.rfcDetail.status.eq(Status.ACTIVE)));
    }

}
