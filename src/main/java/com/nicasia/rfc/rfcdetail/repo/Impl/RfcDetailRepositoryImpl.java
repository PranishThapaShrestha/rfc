package com.nicasia.rfc.rfcdetail.repo.Impl;

import com.nicasia.rfc.rfcdetail.entity.QRfcDetail;
import com.nicasia.rfc.rfcdetail.entity.RequestType;
import com.nicasia.rfc.rfcdetail.entity.RfcApprovalStatus;
import com.nicasia.rfc.rfcdetail.entity.RfcDetail;
import com.nicasia.rfc.rfcdetail.repo.RfcDetailRepository;
import com.nicasia.rfc.rfcdetail.repo.custom.RfcDetailRepositoryCustom;
import com.nicasia.rfc.security.jwt.AuthUtil;
import com.nicasia.rfc.shared.abstracts.BaseRepositoryImpl;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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


    @Override
    public Page<RfcDetail> findAllRequestedRfcDetails(String refCode, RequestType requestType, Pageable pageable) {

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (refCode != "") {
            if (requestType.equals(RequestType.PRE_APPROVAL)) {
                booleanBuilder.and(qRfcDetail.preApprovalMemoCode.eq(refCode));
            }
        }
        if(requestType.equals(RequestType.PRE_APPROVAL)){
            booleanBuilder.and(qRfcDetail.requestedby.id.eq(AuthUtil.getCurrentUser().getId()));
            booleanBuilder.and(qRfcDetail.approvalStatus.eq(RfcApprovalStatus.REQUESTED));
        }

        return repository.findAll(booleanBuilder,pageable);
    }
}
