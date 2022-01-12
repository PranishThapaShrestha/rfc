package com.nicasia.rfc.rfcdetail.service.Impl;

import com.nicasia.rfc.core.email.Mail;
import com.nicasia.rfc.core.email.service.EmailService;
import com.nicasia.rfc.core.usermanagement.user.entity.User;
import com.nicasia.rfc.core.usermanagement.user.service.UserService;
import com.nicasia.rfc.rfcdetail.entity.*;
import com.nicasia.rfc.rfcdetail.repo.RfcSupportApproveDetailRepository;
import com.nicasia.rfc.rfcdetail.service.RfcSupportApproveDetailService;
import com.nicasia.rfc.security.jwt.AuthUtil;
import com.nicasia.rfc.shared.enums.Status;
import com.nicasia.rfc.util.PageResult;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RfcSupportApproveDetailServiceImpl implements RfcSupportApproveDetailService {

    private final UserService userService;
    private final RfcSupportApproveDetailRepository rfcSupportApproveDetailRepository;
    private final EmailService emailService;

    public RfcSupportApproveDetailServiceImpl(UserService userService,
                                              RfcSupportApproveDetailRepository rfcSupportApproveDetailRepository,
                                              EmailService emailService) {
        this.userService = userService;
        this.rfcSupportApproveDetailRepository = rfcSupportApproveDetailRepository;
        this.emailService = emailService;

    }

    @Override
    public void saveSupportApproveDetails(List<Long> approvedByUserIds,
                                          List<Long> supportedByUserIds,
                                          RfcDetail rfcDetail,
                                          RequestType requestType) {

        List<Long> userIds= new ArrayList<>();
        userIds.addAll(approvedByUserIds);
        userIds.addAll(supportedByUserIds);

        Map<Long, User> userMap = userService.findAllUserByIdsIn(userIds).stream().collect(Collectors
                .toMap(user -> user.getId(), o -> o));
        userMap.put(AuthUtil.getCurrentUser().getId(),AuthUtil.getCurrentUser());

        List<RfcSupportApproveDetail> rfcSupportApproveDetails = new ArrayList<>();
        rfcSupportApproveDetails.addAll(approvedByUserIds.stream().map(aLong ->
                saveApproverSupporter(aLong,userMap,rfcDetail,requestType,RequestedForType.APPROVE))
                .collect(Collectors.toList()));

        rfcSupportApproveDetails.addAll(supportedByUserIds.stream().map(aLong ->
                saveApproverSupporter(aLong,userMap,rfcDetail,requestType,RequestedForType.SUPPORT))
                .collect(Collectors.toList()));

        rfcSupportApproveDetails.add(saveApproverSupporter(AuthUtil.getCurrentUser().getId()
                ,userMap,rfcDetail,requestType,RequestedForType.CREATE));


        emailService.pushEmails(emailTo(rfcSupportApproveDetails));
        rfcSupportApproveDetailRepository.saveAll(rfcSupportApproveDetails);
    }

    @Override
    public List<RfcSupportApproveDetail> findRfcSupportApproveDetailById(Long rfcDetailId) {
        return rfcSupportApproveDetailRepository.findAllByRfcId(rfcDetailId);
    }

    public RfcSupportApproveDetail saveApproverSupporter(Long userIds,
                                                         Map<Long, User> userMap,
                                                         RfcDetail rfcDetail,
                                                         RequestType requestType,
                                                         RequestedForType requestedForType) {
        RfcSupportApproveDetail rfcSupportApproveDetail = new RfcSupportApproveDetail();
        rfcSupportApproveDetail.setRequestedForType(requestedForType);
        rfcSupportApproveDetail.setRequestType(requestType);
        rfcSupportApproveDetail.setStatus(Status.ACTIVE);
        rfcSupportApproveDetail.setRfcApprovalStatus(RfcApprovalStatus.REQUESTED);
        rfcSupportApproveDetail.setRfcDetail(rfcDetail);
        rfcSupportApproveDetail.setUser(userMap.get(userIds));
        return rfcSupportApproveDetailRepository.save(rfcSupportApproveDetail);
    }

    @Override
    public PageResult<RfcDetail> findAllExpenditureDetailsWithRequestedForType(String refCode,
                                                                               String requestedFor,
                                                                               Pageable pageable) {
        return rfcSupportApproveDetailRepository.findAllByRequestedForType(refCode, requestedFor, pageable);
    }

    private List<Mail> emailTo(List<RfcSupportApproveDetail> rfcSupportApproveDetails) {

        if (rfcSupportApproveDetails.stream().anyMatch(rfcSupportApproveDetail ->
                rfcSupportApproveDetail.getRequestedForType().equals(RequestedForType.SUPPORT))) {
            rfcSupportApproveDetails.stream().filter(rfcSupportApproveDetail ->
                    !rfcSupportApproveDetail.getRequestedForType().equals(RequestedForType.APPROVE));
        }
        RfcSupportApproveDetail rfcSupportApproveDetail1 = rfcSupportApproveDetails.stream().filter(rfcSupportApproveDetail ->
                rfcSupportApproveDetail.getRequestedForType().equals(RequestedForType.CREATE)).findFirst().get();

        return rfcSupportApproveDetails.stream().filter(rfcSupportApproveDetail ->
                !rfcSupportApproveDetail.getRequestedForType().equals(RequestedForType.CREATE))
                .map(rfcSupportApproveDetail -> convertToMailHelper(rfcSupportApproveDetail,
                        rfcSupportApproveDetail1.getUser().getName())).collect(Collectors.toList());
    }
    

    private Mail convertToMailHelper(RfcSupportApproveDetail rfcSupportApproveDetail, String username) {

        Mail mail = new Mail();
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("Requested by:", username);
        objectMap.put("Name", rfcSupportApproveDetail.getUser().getName());
        objectMap.put("Action", rfcSupportApproveDetail.getRequestedForType().name());
        mail.setModel(objectMap);
        mail.setTo(rfcSupportApproveDetail.getUser().getEmail());
        return mail;


    }
}
