package com.nicasia.rfc.evaluation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class EvaluationResponse {

    private String rfcDetailsName;
    private Date approvedDate;
    private String scope;
    private String timeline;
    private Long cost;
    private String approvedBy;
    private String evaluatorpriority;
    private String alternativesandrecommendation;

}
