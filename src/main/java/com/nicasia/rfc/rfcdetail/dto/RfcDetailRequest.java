package com.nicasia.rfc.rfcdetail.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class RfcDetailRequest {

    private String projectname;
    private Long departmentId;
    private Long requestedBy;
    private Long supportedBy;
    private String unit;
    private Date requestDate;
    private Date dateDecided;
    private List<Long> supportedByUserIds;
    private List<Long> approvedByUserIds;
    private String preApprovalMemoCode;
}
