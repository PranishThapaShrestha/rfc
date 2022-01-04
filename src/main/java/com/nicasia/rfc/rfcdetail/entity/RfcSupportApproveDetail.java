package com.nicasia.rfc.rfcdetail.entity;

import com.nicasia.rfc.core.usermanagement.user.entity.User;
import com.nicasia.rfc.shared.abstracts.BaseEntity;
import com.nicasia.rfc.shared.enums.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class RfcSupportApproveDetail extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "rfc_detail_id")
    private RfcDetail rfcDetail;

    @Column(name = "requested_for_type")
    private RequestedForType requestedForType;

    @Column(name = "request_type")
    private RequestType requestType;

    private Status status;

}
