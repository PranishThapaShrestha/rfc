package com.nicasia.rfc.rfcdetail.service.Impl;

import com.nicasia.rfc.core.email.Mail;
import com.nicasia.rfc.core.email.service.EmailService;
import com.nicasia.rfc.core.usermanagement.user.entity.User;
import com.nicasia.rfc.core.usermanagement.user.service.UserService;
import com.nicasia.rfc.rfcdetail.entity.RequestType;
import com.nicasia.rfc.rfcdetail.entity.RequestedForType;
import com.nicasia.rfc.rfcdetail.entity.RfcDetail;
import com.nicasia.rfc.rfcdetail.entity.RfcSupportApproveDetail;
import com.nicasia.rfc.rfcdetail.repo.RfcSupportApproveDetailRepository;
import com.nicasia.rfc.rfcdetail.service.RfcSupportApproveDetailService;
import com.nicasia.rfc.security.jwt.AuthUtil;
import com.nicasia.rfc.shared.enums.Status;
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

    public RfcSupportApproveDetailServiceImpl(UserService userService, RfcSupportApproveDetailRepository rfcSupportApproveDetailRepository, EmailService emailService) {
        this.userService = userService;
        this.rfcSupportApproveDetailRepository = rfcSupportApproveDetailRepository;
        this.emailService = emailService;
    }

    @Override
    public void saveSupportApproveDetails(List<Long> approvedByUserIds,
                                          List<Long> supportedByUserIds,
                                          RfcDetail rfcDetail,
                                          RequestType requestType) {

        List<Long> users = new ArrayList<>();
        users.addAll(supportedByUserIds);
        users.addAll(approvedByUserIds);

        Map<Long, User> userMap = userService.findAllUserByIdsIn(users).stream()
                .collect(Collectors.toMap(user -> user.getId(), user -> user));
        List<RfcSupportApproveDetail> rfcSupportApproveDetails = new ArrayList<>();

        rfcSupportApproveDetails.addAll(approvedByUserIds.stream().map(aLong ->
                saveApproverSupporter(aLong, userMap, rfcDetail, requestType,
                        RequestedForType.APPROVE)).collect(Collectors.toList()));

        if (supportedByUserIds.size() > 0) {
            rfcSupportApproveDetails.addAll(supportedByUserIds.stream().map(aLong ->
                    saveApproverSupporter(aLong, userMap, rfcDetail, requestType,
                            RequestedForType.SUPPORT)).collect(Collectors.toList()));
        }

        rfcSupportApproveDetails.add(saveApproverSupporter(AuthUtil.getCurrentUser()
                        .getId(), userMap, rfcDetail, requestType,
                RequestedForType.CREATE));


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
        rfcSupportApproveDetail.setRfcDetail(rfcDetail);
        rfcSupportApproveDetail.setUser(userMap.get(userIds));
        return rfcSupportApproveDetailRepository.save(rfcSupportApproveDetail);
    }

    private List<Mail> emailTo(List<RfcSupportApproveDetail> rfcSupportApproveDetails) {

        if (rfcSupportApproveDetails.stream().anyMatch(rfcSupportApproveDetail -> rfcSupportApproveDetail
                .getRequestedForType().equals(RequestedForType.SUPPORT))) {
            rfcSupportApproveDetails.stream().filter(rfcSupportApproveDetail -> !rfcSupportApproveDetail
                    .getRequestedForType().equals(RequestedForType.APPROVE));
        }
        RfcSupportApproveDetail rfcSupportApproveDetail = rfcSupportApproveDetails.stream()
                .filter(rfcSupportApproveDetail1 -> rfcSupportApproveDetail1
                        .getRequestedForType().equals(RequestedForType.CREATE)).findFirst().get();

        return rfcSupportApproveDetails.stream().filter(rfcSupportApproveDetail1 -> !rfcSupportApproveDetail1
                .getRequestedForType().equals(RequestedForType.CREATE)).map(rfcSupportApproveDetail1 ->
                ConvertToEmailHelper(rfcSupportApproveDetail1, rfcSupportApproveDetail.getUser().getName()))
                .collect(Collectors.toList());

    }

    private Mail ConvertToEmailHelper(RfcSupportApproveDetail rfcSupportApproveDetail, String userName) {

        Mail mail = new Mail();
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("Name", rfcSupportApproveDetail.getUser().getName());
        objectMap.put("Action", rfcSupportApproveDetail.getRequestedForType().name());
        objectMap.put("Requested By", userName);
        mail.setModel(objectMap);
        mail.setTo(rfcSupportApproveDetail.getUser().getEmail());
        return mail;

    }
}
