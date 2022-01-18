package com.nicasia.rfc.evaluation.service;

import com.nicasia.rfc.evaluation.dto.EvaluationResponse;
import com.nicasia.rfc.evaluation.entity.Evaluation;
import com.nicasia.rfc.rfcdetail.dto.PutRemarksDto;
import com.nicasia.rfc.rfcdetail.entity.RfcSupportApproveDetail;

import java.util.List;

public interface EvaluationService {

Evaluation addEvaluation(PutRemarksDto putRemarksDto, RfcSupportApproveDetail rfcSupportApproveDetail);

EvaluationResponse findEvaluationBasedOnRfcId(List<RfcSupportApproveDetail> rfcSupportApproveDetails);

}

