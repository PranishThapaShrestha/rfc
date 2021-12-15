package com.nicasia.rfc.evaluation.entity;

import com.nicasia.rfc.rfcdetail.entity.RfcDetail;
import com.nicasia.rfc.shared.abstracts.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Evaluation extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "rfcdetail_id")
    private RfcDetail rfcdetail;

    @Column(name = "scope")
    private String scope;

    @Column(name = "cost")
    private Long cost;

    @Column(name = "timeline")
    private String timeline;

    @Column(name = "costresource")
    private String costresource;

    @Column(name = "risk_basedon_cia")
    private String riskbasedoncia;

    @Column(name = "evaluatorPriority")
    private String evaluatorPriority;

    @Column(name = "alternativesandrecommendation")
    private String alternativesandrecommendation;
}
