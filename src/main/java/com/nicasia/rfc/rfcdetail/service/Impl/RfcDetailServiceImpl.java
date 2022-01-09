package com.nicasia.rfc.rfcdetail.service.Impl;

import com.nicasia.rfc.core.usermanagement.department.service.DepartmentService;
import com.nicasia.rfc.core.usermanagement.user.dto.UserMiniResource;
import com.nicasia.rfc.core.usermanagement.user.service.UserService;
import com.nicasia.rfc.rfcdetail.dto.RfcDetailRequest;
import com.nicasia.rfc.rfcdetail.dto.RfcDetailResponse;
import com.nicasia.rfc.rfcdetail.dto.RfcPreapprovalResponse;
import com.nicasia.rfc.rfcdetail.entity.RequestType;
import com.nicasia.rfc.rfcdetail.entity.RfcApprovalStatus;
import com.nicasia.rfc.rfcdetail.entity.RfcDetail;
import com.nicasia.rfc.rfcdetail.entity.RfcSupportApproveDetail;
import com.nicasia.rfc.rfcdetail.repo.RfcDetailRepository;
import com.nicasia.rfc.rfcdetail.service.RfcDetailConvert;
import com.nicasia.rfc.rfcdetail.service.RfcDetailService;
import com.nicasia.rfc.rfcdetail.service.RfcSupportApproveDetailService;
import com.nicasia.rfc.security.jwt.AuthUtil;
import com.nicasia.rfc.shared.enums.Status;
import com.nicasia.rfc.shared.exception.ClientException;
import com.nicasia.rfc.shared.exception.ResourceNotAvailableException;
import com.nicasia.rfc.shared.succesresponse.SuccessResponse;
import com.nicasia.rfc.util.PageResult;
import com.nicasia.rfc.util.ReferenceCodeUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RfcDetailServiceImpl implements RfcDetailService {


    private final DepartmentService departmentService;
    private final UserService userService;
    private final RfcDetailRepository rfcDetailRepository;
    private final RfcDetailConvert rfcDetailConvert;
    //    private final EmailService emailService;
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
        rfcDetail.setDatedecided(rfcDetailRequest.getDateDecided());
//        rfcDetail.setFiscalYear(fiscalYear);
        rfcDetail.setPreApprovalMemoCode(ReferenceCodeUtil.getRefCode());

        rfcDetail.setRequestdate(rfcDetailRequest.getRequestDate());
        rfcDetail.setUnit(rfcDetail.getUnit());
        if (rfcDetailRequest.getSupportedByUserIds().size() == 0) {
            rfcDetail.setRfcApprovalStatus(RfcApprovalStatus.SUPPORTED);
        } else {
            rfcDetail.setRfcApprovalStatus(RfcApprovalStatus.REQUESTED);
        }
        RfcDetail rfcDetail1 = rfcDetailRepository.save(rfcDetail);
        rfcSupportApproveDetailService.saveSupportApproveDetails(
                rfcDetailRequest.getApprovedByUserIds()
                , rfcDetailRequest.getSupportedByUserIds()
                , rfcDetail1, RequestType.PRE_APPROVAL);

        return SuccessResponse.builder().successMessage("Request for change is created with list of supporters and one approver").build();
    }

    @Override
    public PageResult<RfcPreapprovalResponse> getPreApprovalRfc(String refCode, Pageable pageable) {

        Page<RfcDetail> allRequestedRfcDetails = rfcDetailRepository.findAllRequestedRfcDetails(refCode, RequestType.PRE_APPROVAL, pageable);

        if (allRequestedRfcDetails.getContent().size()==0){
            return new PageResult<>();
        }
        return new PageResult<>(rfcDetailConvert.convertAllToCurrentRfcStatus(allRequestedRfcDetails.getContent(), RequestType.PRE_APPROVAL),
                allRequestedRfcDetails.getNumber(),
                allRequestedRfcDetails.getTotalElements(),
                allRequestedRfcDetails.getTotalPages());
    }

    @Override
    public RfcDetailResponse getPreApprovalRfcDetail(Long rfcDetailId) {

        List<RfcSupportApproveDetail> rfcSupportApproveDetailById = rfcSupportApproveDetailService
                .findRfcSupportApproveDetailById(rfcDetailId);

        List<Long> approversSupportersIds = rfcSupportApproveDetailById.stream()
                .map(rfcSupportApproveDetail -> rfcSupportApproveDetail.getUser().getId())
                .collect(Collectors.toList());

        RfcDetail rfcDetail = findById(rfcDetailId);

        Map<Long, UserMiniResource> userMiniResourceByUserIds = userService.findUserMiniResourceByUserIds(approversSupportersIds);

        return rfcDetailConvert.convertToRfcDetail(rfcSupportApproveDetailById, rfcDetail, userMiniResourceByUserIds);

    }

    @Override
    public RfcDetail findById(Long id) {
        return rfcDetailRepository.findById(id).orElseThrow(
                () -> new ResourceNotAvailableException("Request for change", "rfcdetail", id));
    }

}
