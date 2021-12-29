//package com.nicasia.rfc.attachments.model;
//
//import com.nicasia.rfc.rfcdetail.entity.RfcDetail;
//import com.nicasia.rfc.shared.abstracts.BaseEntity;
//import com.nicasia.rfc.shared.enums.Status;
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//
//@Setter
//@Getter
//@Entity
//public class RfcAttachments extends BaseEntity {
//
//    private String fileName;
//    @Column(name = "original_name")
//    private String originalName;
//
//    @ManyToOne
//    @JoinColumn(name = "rfc_id")
//    private RfcDetail rfcDetail;
//
//    private Status status;
//
//
//}
