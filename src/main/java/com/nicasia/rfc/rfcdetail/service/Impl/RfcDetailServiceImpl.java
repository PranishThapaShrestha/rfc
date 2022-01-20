package com.nicasia.rfc.rfcdetail.service.Impl;

import com.nicasia.rfc.core.email.Mail;
import com.nicasia.rfc.core.email.service.EmailService;
import com.nicasia.rfc.core.usermanagement.department.service.DepartmentService;
import com.nicasia.rfc.core.usermanagement.user.dto.UserMiniResource;
import com.nicasia.rfc.core.usermanagement.user.entity.User;
import com.nicasia.rfc.core.usermanagement.user.service.UserService;
import com.nicasia.rfc.evaluation.service.EvaluationService;
import com.nicasia.rfc.rfcdetail.dto.PutRemarksDto;
import com.nicasia.rfc.rfcdetail.dto.RfcDetailRequest;
import com.nicasia.rfc.rfcdetail.dto.RfcDetailResponse;
import com.nicasia.rfc.rfcdetail.dto.RfcPreapprovalResponse;
import com.nicasia.rfc.rfcdetail.entity.*;
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

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RfcDetailServiceImpl implements RfcDetailService {


    private final DepartmentService departmentService;
    private final UserService userService;
    private final RfcDetailRepository rfcDetailRepository;
    private final RfcDetailConvert rfcDetailConvert;
    private final EmailService emailService;
    private final RfcSupportApproveDetailService rfcSupportApproveDetailService;
    private final EvaluationService evaluationService;


    public RfcDetailServiceImpl(DepartmentService departmentService,
                                UserService userService,
                                RfcDetailRepository rfcDetailRepository,
                                RfcDetailConvert rfcDetailConvert,
                                EmailService emailService, RfcSupportApproveDetailService rfcSupportApproveDetailService, EvaluationService evaluationService) {
        this.departmentService = departmentService;
        this.userService = userService;
        this.rfcDetailRepository = rfcDetailRepository;
        this.rfcDetailConvert = rfcDetailConvert;
        this.emailService = emailService;
        this.rfcSupportApproveDetailService = rfcSupportApproveDetailService;

        this.evaluationService = evaluationService;
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
                , rfcDetail1);

        return SuccessResponse.builder().successMessage("Request for change is created with list of supporters and one approver").build();
    }

    @Override
    public PageResult<RfcPreapprovalResponse> getPreApprovalRfc(String refCode, Pageable pageable) {

        Page<RfcDetail> allRequestedRfcDetails = rfcDetailRepository.findAllRequestedRfcDetails(refCode, pageable);

        if (allRequestedRfcDetails.getContent().size() == 0) {
            return new PageResult<>();
        }
        return new PageResult<>(rfcDetailConvert.convertAllToCurrentRfcStatus(allRequestedRfcDetails.getContent()),
                allRequestedRfcDetails.getNumber(),
                allRequestedRfcDetails.getTotalElements(),
                allRequestedRfcDetails.getTotalPages());
    }

    @Override
    public RfcDetailResponse getPreApprovalRfcDetail(Long rfcDetailId) {

        List<RfcSupportApproveDetail> rfcSupportApproveDetails = rfcSupportApproveDetailService
                .findAllRfcSupportApproveDetailByRfcId(rfcDetailId);

        List<Long> userIds = rfcSupportApproveDetails.stream().map(rfcSupportApproveDetail -> rfcSupportApproveDetail
                .getUser().getId()).collect(Collectors.toList());

        RfcDetail rfcDetail = findById(rfcDetailId);

        Map<Long, UserMiniResource> userMiniResourceByUserIds = userService.findUserMiniResourceByUserIds(userIds);

        return
                rfcDetailConvert.convertToRfcDetail(rfcSupportApproveDetails, rfcDetail, userMiniResourceByUserIds);

    }


    @Override
    public PageResult<RfcPreapprovalResponse> getAllRequestedForPreApprovalDetails(String refCode, String requestedFor, Pageable pageable) {
        PageResult<RfcDetail> allExpenditureDetailsWithRequestedForType = rfcSupportApproveDetailService
                .findAllExpenditureDetailsWithRequestedForType(
                        refCode,
                        requestedFor,
                        pageable);

        List<RfcDetail> results = allExpenditureDetailsWithRequestedForType.getResults();
        return new PageResult<>(rfcDetailConvert.convertAllToCurrentRfcStatus
                (results),
                allExpenditureDetailsWithRequestedForType.getPage(),
                allExpenditureDetailsWithRequestedForType.getTotalResult(), allExpenditureDetailsWithRequestedForType.getTotalPages());
    }

    @Override
    public SuccessResponse putRemarks(PutRemarksDto putRemarksDto, Long rfcDetailId) {

        List<RfcSupportApproveDetail> allRfcSupportApproveDetailByRfcId = rfcSupportApproveDetailService
                .findAllRfcSupportApproveDetailByRfcId(rfcDetailId);
        User currentUser = AuthUtil.getCurrentUser();

        Optional<RfcSupportApproveDetail> first = allRfcSupportApproveDetailByRfcId.stream()
                .filter(rfcSupportApproveDetail -> (rfcSupportApproveDetail.getUser().getId().equals(currentUser.getId()))
                        &&
                        (rfcSupportApproveDetail.getRfcApprovalStatus().equals(putRemarksDto.getStatus()))).findFirst();

        if (first.isPresent()) {
            throw new ClientException("Dear user the action has already been done");
        }

        if (allRfcSupportApproveDetailByRfcId != null && allRfcSupportApproveDetailByRfcId.size() > 0) {

            RfcSupportApproveDetail rfcSupportApproveDetail1 = (allRfcSupportApproveDetailByRfcId.stream().filter(rfcSupportApproveDetail -> rfcSupportApproveDetail
                    .getUser().getId().equals(currentUser.getId()))).findFirst()
                    .orElseThrow(() -> new ClientException("Details not found"));


            //
            if (putRemarksDto.getStatus().equals(RfcApprovalStatus.APPROVED.name())) {
                if (!didAllSupported(allRfcSupportApproveDetailByRfcId)) {
                    throw new ClientException("Sorry this request is still not supported by everyone");
                }
            }
            rfcSupportApproveDetail1.setRfcApprovalStatus(RfcApprovalStatus.valueOf(putRemarksDto.getStatus()));
            rfcSupportApproveDetailService.saveDetail(rfcSupportApproveDetail1);
            finalUpdateOfRfcDetail(rfcDetailId, putRemarksDto, allRfcSupportApproveDetailByRfcId);
            //TODO still need to work on this
            return SuccessResponse.builder().successMessage("" + putRemarksDto.getStatus() + "successfull").build();
        }
        throw new ClientException("error");
    }

    private void finalUpdateOfRfcDetail(Long expenditureId,
                                        PutRemarksDto putRemarksDto,
                                        List<RfcSupportApproveDetail> allRfcSupportApproveDetailsById) {

        RfcSupportApproveDetail rfcSupportApproveDetail1 = allRfcSupportApproveDetailsById
                .stream().filter(rfcSupportApproveDetail -> rfcSupportApproveDetail
                        .getRequestedForType().equals(RequestedForType.CREATE)).findFirst().get();

        List<RfcSupportApproveDetail> rfcSupportApproveDetails = allRfcSupportApproveDetailsById.stream()
                .filter(rfcSupportApproveDetail -> rfcSupportApproveDetail.getRequestedForType()
                        .equals(RequestedForType.SUPPORT)).collect(Collectors.toList());

        RfcSupportApproveDetail rfcSupportApproveDetail2 = (allRfcSupportApproveDetailsById.stream()
                .filter(rfcSupportApproveDetail -> rfcSupportApproveDetail
                        .getRequestedForType().equals(RequestedForType.APPROVE)).findFirst()).get();

        RfcDetail rfcDetail = rfcDetailRepository.findById(expenditureId).get();

        if (putRemarksDto.getStatus().equals(RfcApprovalStatus.SUPPORTED.name())) {
            if (didAllSupported(allRfcSupportApproveDetailsById)) {
                rfcDetail.setRfcApprovalStatus(RfcApprovalStatus.SUPPORTED);
                rfcDetail.setDatedecided(new Date());
                emailService.pushMails(getMailHelper(allRfcSupportApproveDetailsById,
                        allRfcSupportApproveDetailsById.get(0).getRfcDetail().getPreApprovalMemoCode()));
            }
        }

        if (putRemarksDto.getStatus().equals(RfcApprovalStatus.APPROVED.name())) {
            if ((rfcSupportApproveDetails.size() > 0 && rfcDetail.getRfcApprovalStatus().equals(RfcApprovalStatus.SUPPORTED))
                    || (rfcSupportApproveDetails.size() == 0 && (rfcDetail.getRfcApprovalStatus().equals(RfcApprovalStatus.REQUESTED))
                    || rfcDetail.getRfcApprovalStatus().equals(RfcApprovalStatus.SUPPORTED))) {
                rfcDetail.setDatedecided(new Date());
                rfcDetail.setRfcApprovalStatus(RfcApprovalStatus.APPROVED);
                evaluationService.addEvaluation(putRemarksDto, rfcSupportApproveDetail2);
                emailService.pushSupportedApprovedMail(mailToSingle(RequestedForType.APPROVE.name(),
                        rfcSupportApproveDetail1.getUser().getName(),
                        rfcSupportApproveDetail1.getUser().getEmail(),
                        rfcSupportApproveDetail1.getRfcDetail().getPreApprovalMemoCode()));
            } else {
                throw new ClientException("Sorry this request is not yet supported by all supporters");
            }
        }
    }


    private boolean didAllSupported(List<RfcSupportApproveDetail> rfcSupportApproveDetails) {

        List<RfcSupportApproveDetail> collect = rfcSupportApproveDetails.stream().filter(rfcSupportApproveDetail -> (rfcSupportApproveDetail
                .getRequestedForType().equals(RequestedForType.SUPPORT))
                &&
                (rfcSupportApproveDetail.getRfcApprovalStatus().equals(RfcApprovalStatus.REQUESTED))).collect(Collectors.toList());

        if (collect.size() > 0) {
            return false;
        }
        return true;
    }

    @Override
    public RfcDetail findById(Long id) {
        return rfcDetailRepository.findById(id).orElseThrow(
                () -> new ResourceNotAvailableException("Request for change", "rfcdetail", id));
    }

    private List<Mail> getMailHelper(List<RfcSupportApproveDetail> rfcSupportApproveDetails, String referenceCode) {

        RfcSupportApproveDetail rfcSupportApproveDetail1 = rfcSupportApproveDetails.stream().filter(rfcSupportApproveDetail ->
                rfcSupportApproveDetail.getRequestedForType().equals(RequestedForType.CREATE)).findFirst().get();

        rfcSupportApproveDetails = rfcSupportApproveDetails.stream()
                .filter(rfcSupportApproveDetail -> rfcSupportApproveDetail.getRequestedForType()
                        .equals(RequestedForType.APPROVE)).collect(Collectors.toList());

        return rfcSupportApproveDetails.stream().filter(rfcSupportApproveDetail -> !rfcSupportApproveDetail.getRequestedForType()
                .equals(RequestedForType.CREATE)).map(rfcSupportApproveDetail -> mailToHelper(rfcSupportApproveDetail
                , referenceCode, rfcSupportApproveDetail1.getUser().getName())).collect(Collectors.toList());

    }

    private Mail mailToHelper(RfcSupportApproveDetail rfcSupportApproveDetail, String refCode, String name) {
        Mail mail = new Mail();
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("Name", rfcSupportApproveDetail.getUser().getName());
        objectMap.put("Action", rfcSupportApproveDetail.getRequestedForType().name());
        objectMap.put("Code", refCode);
        objectMap.put("requestedBy", name);
        mail.setSubject("Request For Change");
        mail.setTo(rfcSupportApproveDetail.getUser().getEmail());
        mail.setModel(objectMap);
        return mail;
    }

    private Mail mailToSingle(String requestedFor, String fullName, String email, String memoCode) {
        Mail mail = new Mail();
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("name", fullName);
        objectMap.put("updatedBy", AuthUtil.getCurrentUser().getName());
        objectMap.put("action", requestedFor);
        objectMap.put("memoCode", memoCode);
        mail.setTo(email);
        mail.setModel(objectMap);
        mail.setSubject("Request For Change");
        return mail;
    }


}
