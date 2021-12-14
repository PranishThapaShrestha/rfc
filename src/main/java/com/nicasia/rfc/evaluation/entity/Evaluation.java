package com.nicasia.rfc.evaluation.entity;

import com.nicasia.rfc.rfcdetail.entity.RfcDetail;
import com.nicasia.rfc.shared.abstracts.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Evaluation extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "rfcdetails")
    private RfcDetail rfcDetail;

    @Column(name = "scope")
    private String scope;

    @Column(name = "cost")
    private Long cost;

    @Column(name = "timeline")
    private String timeline;

    @Column(name = "costresource")
    private String costresource;

    @Column(name = "risk-basedon-cia")
    private String riskbasedoncia;

    @Column(name ="evaluatorPriority")
    private EvaluatorPriority evaluatorPriority;

    @Column(name = "alternativesandrecommendation")
    private String alternativesandrecommendation;
}
