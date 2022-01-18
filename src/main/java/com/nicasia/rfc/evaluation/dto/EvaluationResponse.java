package com.nicasia.rfc.evaluation.dto;

import com.nicasia.rfc.core.usermanagement.user.dto.UserMiniResource;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class EvaluationResponse {

    private String rfcDetailsName;
    private UserMiniResource user;
    private Date approvedDate;
    private String scope;
    private String timeline;
    private Long cost;
    private String commentedBy;
    private String evaluatorpriority;
    private String alternativesandrecommendation;

}
