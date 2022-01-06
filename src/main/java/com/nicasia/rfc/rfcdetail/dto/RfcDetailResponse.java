package com.nicasia.rfc.rfcdetail.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Builder
@Getter
@Setter
public class RfcDetailResponse {

    private String projectname;
    private List<SupportedApprovedDetail> supportedApprovedDetails;
    private String unit;
    private Date requestdate;



}
