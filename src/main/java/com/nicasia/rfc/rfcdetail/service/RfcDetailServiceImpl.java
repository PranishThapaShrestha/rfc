package com.nicasia.rfc.rfcdetail.service;

import com.nicasia.rfc.core.usermanagement.department.service.DepartmentService;
import com.nicasia.rfc.core.usermanagement.user.service.UserService;
import com.nicasia.rfc.rfcdetail.dto.RfcDetailRequest;
import com.nicasia.rfc.rfcdetail.entity.ApprovalStatus;
import com.nicasia.rfc.rfcdetail.entity.RfcDetail;
import com.nicasia.rfc.rfcdetail.repo.RfcDetailRepository;
import com.nicasia.rfc.shared.enums.Status;
import com.nicasia.rfc.shared.exception.ResourceNotAvailableException;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
public class RfcDetailServiceImpl implements RfcDetailService {


    private final DepartmentService departmentService;
    private final UserService userService;
    private final RfcDetailRepository rfcDetailRepository;

    public RfcDetailServiceImpl(DepartmentService departmentService, UserService userService, RfcDetailRepository rfcDetailRepository) {
        this.departmentService = departmentService;
        this.userService = userService;
        this.rfcDetailRepository = rfcDetailRepository;
    }

    @Override
    public RfcDetail createRfcDetail(RfcDetailRequest rfcDetailRequest) {
        RfcDetail rfcDetail = new RfcDetail();
        rfcDetail.setProjectname(rfcDetailRequest.getProjectname());
        rfcDetail.setDeparmentname(departmentService.findById(rfcDetailRequest.getDepartmentId()));
        rfcDetail.setRequestedby(userService.findById(rfcDetailRequest.getSupportedBy()).get());
        return getRfcDetail(rfcDetailRequest, rfcDetail);
    }

    @Override
    public RfcDetail updateRfcDetail(Long id, RfcDetailRequest rfcDetailRequest) {

        RfcDetail rfcDetail = rfcDetailRepository.findById(id).orElseThrow
                (() -> new ResourceNotAvailableException("RfcDetails", "byId", id));
        rfcDetail.setProjectname(rfcDetailRequest.getProjectname());
        rfcDetail.setDeparmentname(departmentService.findById(rfcDetailRequest.getDepartmentId()));
        rfcDetail.setRequestedby(userService.findById(rfcDetailRequest.getRequestedBy()).get());
        return getRfcDetail(rfcDetailRequest, rfcDetail);
    }

    @NotNull
    private RfcDetail getRfcDetail(RfcDetailRequest rfcDetailRequest, RfcDetail rfcDetail) {
        rfcDetail.setSupportedby(userService.findById(rfcDetailRequest.getSupportedBy()).get());
        rfcDetail.setUnit(rfcDetailRequest.getUnit());
        rfcDetail.setRequestdate(rfcDetailRequest.getRequestDate());
        rfcDetail.setDatedecided(rfcDetailRequest.getDateDecided());
        rfcDetail.setApprovalStatus(ApprovalStatus.PENDING);
        return rfcDetailRepository.save(rfcDetail);
    }

    @Override
    public RfcDetail removeRfcDetail(Long id) {
        RfcDetail rfcDetail = rfcDetailRepository.findById(id).orElseThrow
                (() -> new ResourceNotAvailableException("RfcDetail", "id", id));
        rfcDetail.setStatus(Status.INACTIVE);
        return rfcDetailRepository.save(rfcDetail);
    }


}
