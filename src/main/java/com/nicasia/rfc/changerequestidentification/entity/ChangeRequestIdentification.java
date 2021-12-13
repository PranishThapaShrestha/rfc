package com.nicasia.rfc.changerequestidentification.entity;

import com.nicasia.rfc.rfcdetail.entity.RfcDetail;
import com.nicasia.rfc.shared.abstracts.BaseEntity;
import com.nicasia.rfc.shared.enums.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class ChangeRequestIdentification extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "rfcdetail_id")
    private RfcDetail rfcdetail;

    private String changerequestidentification;

    private Status status;

}
