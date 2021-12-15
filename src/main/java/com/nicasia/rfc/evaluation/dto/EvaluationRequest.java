package com.nicasia.rfc.evaluation.dto;

import com.nicasia.rfc.evaluation.entity.EvaluatorPriority;
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
    private EvaluatorPriority priority;
    private String alternativesandrecommendation;

}
