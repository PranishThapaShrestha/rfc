package com.nicasia.rfc.changerequestdetails.entity;

import com.nicasia.rfc.rfcdetail.entity.RfcDetail;
import com.nicasia.rfc.shared.abstracts.BaseEntity;
import com.nicasia.rfc.shared.enums.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
public class ChangeRequestDetails extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "rfcdetail_id")
    private RfcDetail rfcDetail;

    @Column(name = "description")
    private String description;

    @Column(name = "justification")
    private String Justification;

    @Column(name = "priority")
    private Priority priority;

    @Column(name = "status")
    private Status status;
}
