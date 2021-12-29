//package com.nicasia.rfc.attachments.repo;
//
//import com.nicasia.rfc.attachments.model.QRfcAttachments;
//import com.nicasia.rfc.attachments.model.RfcAttachments;
//import com.nicasia.rfc.shared.abstracts.BaseRepositoryImpl;
//import com.nicasia.rfc.shared.enums.Status;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Lazy;
//
//import java.util.List;
//
//public class RfcAttachmentsServiceImpl extends BaseRepositoryImpl<RfcAttachments, RfcAttachmentsRepository>
//        implements RfcAttachmentsRepositoryCustom {
//
//    QRfcAttachments qRfcAttachments = QRfcAttachments.rfcAttachments;
//
//    public RfcAttachmentsServiceImpl() {
//        super(RfcAttachments.class);
//    }
//
//    @Lazy
//    @Autowired
//    public void setRepository(RfcAttachmentsRepository rfcAttachmentsRepository) {
//        this.repository = rfcAttachmentsRepository;
//    }
//
//    @Override
//    public List<RfcAttachments> findAllRfcDetailsById(Long rfcAttachmentsId) {
//        return (List<RfcAttachments>) repository.findAll(qRfcAttachments.rfcDetail.id
//                .eq(rfcAttachmentsId).and(qRfcAttachments.status.eq(Status.ACTIVE)));
//
//
//    }
//}
