package com.nicasia.rfc.rfcdetail.service.Impl;

import com.nicasia.rfc.core.usermanagement.department.service.DepartmentService;
import com.nicasia.rfc.core.usermanagement.user.service.UserService;
import com.nicasia.rfc.rfcdetail.dto.AddRemarksDto;
import com.nicasia.rfc.rfcdetail.dto.RfcDetailRequest;
import com.nicasia.rfc.rfcdetail.dto.RfcDetailResponse;
import com.nicasia.rfc.rfcdetail.entity.ApprovalStatus;
import com.nicasia.rfc.rfcdetail.entity.RequestType;
import com.nicasia.rfc.rfcdetail.entity.RfcDetail;
import com.nicasia.rfc.rfcdetail.repo.RfcDetailRepository;
import com.nicasia.rfc.rfcdetail.service.RfcDetailConvert;
import com.nicasia.rfc.rfcdetail.service.RfcDetailService;
import com.nicasia.rfc.rfcdetail.service.RfcSupportApproveDetailService;
import com.nicasia.rfc.security.jwt.AuthUtil;
import com.nicasia.rfc.shared.enums.Status;
import com.nicasia.rfc.shared.exception.ClientException;
import com.nicasia.rfc.shared.exception.ResourceNotAvailableException;
import com.nicasia.rfc.shared.succesresponse.SuccessResponse;
import com.nicasia.rfc.util.ReferenceCodeUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RfcDetailServiceImpl implements RfcDetailService {


    private final DepartmentService departmentService;
    private final UserService userService;
    private final RfcDetailRepository rfcDetailRepository;
    private final RfcDetailConvert rfcDetailConvert;
    private final RfcSupportApproveDetailService rfcSupportApproveDetailService;


    public RfcDetailServiceImpl(DepartmentService departmentService,
                                UserService userService,
                                RfcDetailRepository rfcDetailRepository,
                                RfcDetailConvert rfcDetailConvert,
                                RfcSupportApproveDetailService rfcSupportApproveDetailService) {
        this.departmentService = departmentService;
        this.userService = userService;
        this.rfcDetailRepository = rfcDetailRepository;
        this.rfcDetailConvert = rfcDetailConvert;
        this.rfcSupportApproveDetailService = rfcSupportApproveDetailService;

    }

    @Override
    public SuccessResponse createPreApprovalRfcDetail(RfcDetailRequest rfcDetailRequest) {

        if (rfcDetailRequest.getApprovedByUserIds().size() > 1) {
            throw new ClientException("Dear user, multiple approver is not allowed");
        }
//        FiscalYear fiscalYear=fiscalYearRepository.findById(rfcDetailRequest.getFiscalYearId()).get();
        RfcDetail rfcDetail = new RfcDetail();
        rfcDetail.setProjectname(rfcDetailRequest.getProjectname());
        rfcDetail.setDeparmentname(AuthUtil.getCurrentUser().getDepartment());
        rfcDetail.setRequestedby(AuthUtil.getCurrentUser());
        rfcDetail.setStatus(Status.ACTIVE);
        rfcDetail.setApprovalStatus(ApprovalStatus.PENDING);
        rfcDetail.setDatedecided(rfcDetailRequest.getDateDecided());
//        rfcDetail.setFiscalYear(fiscalYear);
        rfcDetail.setPreApprovalMemoCode(ReferenceCodeUtil.getRefCode());

        rfcDetail.setRequestdate(rfcDetailRequest.getRequestDate());
        rfcDetail.setUnit(rfcDetail.getUnit());
        if (rfcDetailRequest.getSupportedByUserIds().size() == 0) {
            rfcDetail.setApprovalStatus(ApprovalStatus.SUPPORTED);
        } else {
            rfcDetail.setApprovalStatus(ApprovalStatus.REQUESTED);
        }
        RfcDetail rfcDetail1=rfcDetailRepository.save(rfcDetail);
       rfcSupportApproveDetailService.saveSupportApproveDetails(
               rfcDetailRequest.getApprovedByUserIds()
               ,rfcDetailRequest.getSupportedByUserIds()
               ,rfcDetail1, RequestType.PRE_APPROVAL);

        return SuccessResponse.builder().successMessage("Request for change is created with list of supporters and one approver").build();

    }

    @Override
    public RfcDetailResponse updateRfcDetail(Long id, RfcDetailRequest rfcDetailRequest) {

        RfcDetail rfcDetail = rfcDetailRepository.findById(id).orElseThrow
                (() -> new ResourceNotAvailableException("RfcDetails", "byId", id));
        rfcDetail.setProjectname(rfcDetailRequest.getProjectname());
        rfcDetail.setDeparmentname(departmentService.findById(rfcDetailRequest.getDepartmentId()));
        rfcDetail.setRequestedby(userService.findById(rfcDetailRequest.getRequestedBy()));
        rfcDetail.setUnit(rfcDetailRequest.getUnit());
        rfcDetailRepository.save(rfcDetail);
        return rfcDetailConvert.convertOne(rfcDetail);

    }

    @NotNull
    private RfcDetail getRfcDetail(RfcDetailRequest rfcDetailRequest, RfcDetail rfcDetail) {
        rfcDetail.setUnit(rfcDetailRequest.getUnit());
        rfcDetail.setRequestdate(rfcDetailRequest.getRequestDate());
        rfcDetail.setDatedecided(rfcDetailRequest.getDateDecided());
        rfcDetail.setApprovalStatus(ApprovalStatus.PENDING);
        return rfcDetailRepository.save(rfcDetail);
    }

    @Override
    public RfcDetailResponse removeRfcDetail(Long id) {
        RfcDetail rfcDetail = rfcDetailRepository.findById(id).orElseThrow
                (() -> new ResourceNotAvailableException("RfcDetail", "id", id));
        rfcDetail.setStatus(Status.INACTIVE);

        return rfcDetailConvert.convertOne(rfcDetailRepository.save(rfcDetail));
    }

    @Override
    public RfcDetail findById(Long id) {
        return rfcDetailRepository.findById(id).orElseThrow
                (() -> new ResourceNotAvailableException("RfcDetail", "id", id));

    }

    @Override
    public SuccessResponse addRemarks(AddRemarksDto addRemarksDto, Long rfcDetailsId) {
        return null;
    }

    @Override
    public List<RfcDetailResponse> retrieveAllRfcDetails() {
        return rfcDetailConvert.convertAll((List<RfcDetail>) rfcDetailRepository.findAll());

    }
}
