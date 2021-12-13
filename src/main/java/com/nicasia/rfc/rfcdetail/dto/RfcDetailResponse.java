package com.nicasia.rfc.rfcdetail.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
@Getter
@Setter
public class RfcDetailResponse {

    private String projectname;
    private String requestedby;
    private String deparmentname;
    private String supportedby;
    private String unit;
    private Date requestdate;
    private Date datedecided;
    private String approvalStatus;
    private String status;



}
