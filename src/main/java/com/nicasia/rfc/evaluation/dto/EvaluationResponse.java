package com.nicasia.rfc.evaluation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EvaluationResponse {

    private Long id;
    private String rfcdetailsname;
    private String scope;
    private String timeline;
    private Long cost;
    private String evaluatorpriority;
    private String alternativesandrecommendation;

}
