package com.nicasia.rfc.evaluation.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EvaluationRequest {

    private String scope;
    private String timeline;
    private Long cost;
    private String costresource;
    private String riskbasedoncia;
    private String alternativesandrecommendation;

}
