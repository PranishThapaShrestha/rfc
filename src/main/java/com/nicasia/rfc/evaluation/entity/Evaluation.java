package com.nicasia.rfc.evaluation.entity;

import com.nicasia.rfc.rfcdetail.entity.RfcSupportApproveDetail;
import com.nicasia.rfc.shared.abstracts.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Evaluation extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "rfc_support_approve_id")
    private RfcSupportApproveDetail rfcSupportApproveDetail;

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
    private EvaluatorPriority evaluatorPriority;

    @Column(name = "alternativesandrecommendation")
    private String alternativesandrecommendation;
}
