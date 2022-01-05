package com.nicasia.rfc.rfcdetail.entity;

import com.nicasia.rfc.core.usermanagement.department.entity.Department;
import com.nicasia.rfc.core.usermanagement.user.entity.User;
import com.nicasia.rfc.shared.abstracts.BaseEntity;
import com.nicasia.rfc.shared.enums.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Getter
@Setter
@Entity
public class RfcDetail extends BaseEntity {

    @Column(name = "project_name")
    private String projectname;

    @ManyToOne
    @JoinColumn (name = "requested_by")
    private User requestedby;

    @ManyToOne
    @JoinColumn(name = "department_name")
    private Department deparmentname;

    @Column(name = "pre_approval_memo_code", length = 30)
    private String preApprovalMemoCode;

//    @ManyToOne
//    @JoinColumn(name = "fiscal_year_id")
//    private FiscalYear fiscalYear;

    @Column(name = "unit")
    private String unit;

    @Column(name = "request_date")
    private Date requestdate;

    @Column(name = "date_decided")
    private Date datedecided;

    private ApprovalStatus approvalStatus;

    private Status status;

}
