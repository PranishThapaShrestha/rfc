package com.nicasia.rfc.evaluation.service;

import com.nicasia.rfc.evaluation.dto.EvaluationRequest;
import com.nicasia.rfc.evaluation.dto.EvaluationResponse;

import java.util.List;

public interface EvaluationService {

    EvaluationResponse createEvaluation(Long id, EvaluationRequest evaluationRequest);

    EvaluationResponse findEvaluationById(Long id);

    List<EvaluationResponse> getAllEvaluation();

    EvaluationResponse updateEvaluation(Long id,EvaluationRequest evaluationRequest);
}
