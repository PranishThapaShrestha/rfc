package com.nicasia.rfc.evaluation.service;

import com.nicasia.rfc.evaluation.dto.EvaluationResponse;
import com.nicasia.rfc.evaluation.entity.Evaluation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EvaluationConvertImpl implements EvaluationConvert {

    @Override
    public EvaluationResponse convertOne(Evaluation evaluation) {
        return convertHelper(evaluation);
    }

    @Override
    public List<EvaluationResponse> convertAll(List<Evaluation> evaluations) {

        return evaluations.stream()
                .map(evaluation -> convertOne(evaluation)).collect(Collectors.toList());
    }

    private EvaluationResponse convertHelper(Evaluation evaluation) {
        return EvaluationResponse.builder()
                .id(evaluation.getId())
                .rfcdetailsname(evaluation.getRfcdetail().getProjectname())
                .cost(evaluation.getCost())
                .scope(evaluation.getScope())
                .timeline(evaluation.getTimeline())
                .evaluatorpriority(evaluation.getEvaluatorPriority())
                .alternativesandrecommendation(evaluation.getAlternativesandrecommendation())
                .build();
    }

}
