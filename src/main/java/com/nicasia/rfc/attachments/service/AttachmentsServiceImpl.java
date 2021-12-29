//package com.nicasia.rfc.attachments.service;
//
//import com.jlefebure.spring.boot.minio.MinioException;
//import com.jlefebure.spring.boot.minio.MinioService;
//import com.nicasia.rfc.attachments.model.RfcAttachments;
//import com.nicasia.rfc.attachments.repo.RfcAttachmentsRepository;
//import com.nicasia.rfc.rfcdetail.entity.RfcDetail;
//import com.nicasia.rfc.shared.enums.Status;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class AttachmentsServiceImpl implements AttachmentsService {
//    @Value("${spring.minio.endPoint}")
//    private String endPoint;
//
//    @Value("${spring.minio.port}")
//    private String port;
//
//    @Value("${spring.minio.access-key}")
//    private String accessKey;
//
//    @Value("${spring.minio.secret-key}")
//    private String accessSecret;
//
//    @Value("${spring.minio.bucket}")
//    private String bucket;
//
//    @Value("${spring.minio.expiresIn}")
//    private String expiresIn;
//
//
//
//    private final  MinioService minioService;
//    private final RfcAttachmentsRepository rfcAttachmentsRepository;
//
//    public AttachmentsServiceImpl(MinioService minioService, RfcAttachmentsRepository rfcAttachmentsRepository) {
//        this.minioService = minioService;
//        this.rfcAttachmentsRepository = rfcAttachmentsRepository;
//    }
//
//
//    @Override
//    public List<RfcAttachments> uploadFile(RfcDetail rfcDetail, MultipartFile[] files, boolean isEdit) {
//        List<RfcAttachments> rfcAttachments1 =new ArrayList<>();
//
//        if(isEdit){
//            List<RfcAttachments> rfcAttachmentsList=rfcAttachmentsRepository.findAllRfcDetailsById(rfcDetail.getId());
//            rfcAttachmentsRepository
//                    .saveAll(rfcAttachmentsList.stream()
//                            .map(rfcAttachments -> convertToInactive(rfcAttachments)).collect(Collectors.toList()));
//        }
//
//        for (MultipartFile file : files) {
//            String filename = System.currentTimeMillis() + file.getOriginalFilename();
//            Path path = Paths.get(filename);
//            try {
//                minioService.upload(path,file.getInputStream(),file.getContentType());
//                RfcAttachments rfcAttachments=new RfcAttachments();
//                rfcAttachments.setFileName(filename);
//                rfcAttachments.setOriginalName(file.getOriginalFilename());
//                rfcAttachments.setStatus(Status.ACTIVE);
//                rfcAttachments.setRfcDetail(rfcDetail);
//                rfcAttachments1.add(rfcAttachments);
//            }catch (MinioException | IOException e){
//                e.printStackTrace();
//            }
//        }return rfcAttachments1;
//
//    }
//
//    private RfcAttachments convertToInactive(RfcAttachments rfcAttachments) {
//        rfcAttachments.setStatus(Status.INACTIVE);
//        return rfcAttachments;
//    }
//
//}
