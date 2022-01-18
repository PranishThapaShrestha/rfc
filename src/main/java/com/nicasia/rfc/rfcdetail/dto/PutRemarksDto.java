package com.nicasia.rfc.rfcdetail.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PutRemarksDto {

    private String status;
    private String comment;
    private String scope;
    private String timeline;
    private Long cost;
    private String costresource;
    private String riskbasedoncia;
    private String priority;
    private String alternativesandrecommendation;


}
