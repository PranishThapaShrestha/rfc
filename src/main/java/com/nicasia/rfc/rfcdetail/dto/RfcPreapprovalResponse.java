package com.nicasia.rfc.rfcdetail.dto;

import com.nicasia.rfc.shared.enums.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class RfcPreapprovalResponse {

    private String projectName;
    private Status status;
    private String currentApprovalStatus;
    private Date requestedDate;


}
