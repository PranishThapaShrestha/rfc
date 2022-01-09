package com.nicasia.rfc.rfcdetail.repo.Impl;

import com.nicasia.rfc.rfcdetail.entity.*;
import com.nicasia.rfc.rfcdetail.repo.RfcSupportApproveDetailRepository;
import com.nicasia.rfc.rfcdetail.repo.custom.RfcSupportApproveDetailRepositoryCustom;
import com.nicasia.rfc.security.jwt.AuthUtil;
import com.nicasia.rfc.shared.abstracts.BaseRepositoryImpl;
import com.nicasia.rfc.shared.enums.Status;
import com.nicasia.rfc.util.PageResult;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class RfcSupportApproveDetailRepositoryImpl extends BaseRepositoryImpl<RfcSupportApproveDetail, RfcSupportApproveDetailRepository> implements RfcSupportApproveDetailRepositoryCustom {

    QRfcSupportApproveDetail qRfcSupportApproveDetail = QRfcSupportApproveDetail.rfcSupportApproveDetail;

    public RfcSupportApproveDetailRepositoryImpl() {
        super(RfcSupportApproveDetail.class);
    }

    @Lazy
    @Autowired
    public void setRepository(RfcSupportApproveDetailRepository rfcSupportApproveDetailRepository) {
        this.repository = rfcSupportApproveDetailRepository;
    }

    @Override
    public List<RfcSupportApproveDetail> findAllByRfcId(Long rfcId) {

        return (List<RfcSupportApproveDetail>) repository.findAll(qRfcSupportApproveDetail.rfcDetail.id.eq(rfcId)
                .and(qRfcSupportApproveDetail.rfcDetail.status.eq(Status.ACTIVE)));
    }

    @Override
    public PageResult<RfcDetail> findAllByRequestedForType(String refCode,
                                                           String requestedFor,
                                                           RequestType requestType,
                                                           Pageable pageable) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        booleanBuilder.and(qRfcSupportApproveDetail.rfcApprovalStatus.ne(RfcApprovalStatus.RETURNED));
        booleanBuilder.and(qRfcSupportApproveDetail.requestedForType.eq(RequestedForType.valueOf(requestedFor)));
        booleanBuilder.and(qRfcSupportApproveDetail.user.eq(AuthUtil.getCurrentUser()));
        booleanBuilder.and(qRfcSupportApproveDetail.status.eq(Status.ACTIVE));

        List<RfcSupportApproveDetail> fetch = from(qRfcSupportApproveDetail).where(booleanBuilder)
                .offset((pageable.getPageNumber()) * pageable.getPageSize())
                .limit(pageable.getPageSize())
                .orderBy(qRfcSupportApproveDetail.rfcDetail.lastModified.asc())
                .fetch();
        long count = from(qRfcSupportApproveDetail).where(booleanBuilder).fetchCount();
        List<RfcDetail> list = new ArrayList<>();
        for (RfcSupportApproveDetail supportApproveDetail : fetch) {
            RfcDetail rfcDetail = convertToRfcDetail(supportApproveDetail);
            list.add(rfcDetail);
        }
        PageResult<RfcDetail> rfcDetailPageResult = new PageResult<RfcDetail>(
                list,
                pageable.getPageNumber(),
                count,
                (int) (count / pageable.getPageSize()));

        return rfcDetailPageResult;
    }

    private RfcDetail convertToRfcDetail(RfcSupportApproveDetail rfcSupportApproveDetail) {
        return rfcSupportApproveDetail.getRfcDetail();
    }

}
