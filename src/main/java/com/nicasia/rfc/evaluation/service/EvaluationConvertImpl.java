package com.nicasia.rfc.evaluation.service;

import com.nicasia.rfc.core.usermanagement.user.entity.User;
import com.nicasia.rfc.evaluation.dto.EvaluationResponse;
import com.nicasia.rfc.evaluation.entity.Evaluation;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EvaluationConvertImpl implements EvaluationConvert {
    @Override
    public EvaluationResponse convertOne(Evaluation evaluation, User user) {
        return EvaluationResponse.builder()
                .scope(evaluation.getScope())
                .alternativesandrecommendation(evaluation.getAlternativesandrecommendation())
                .timeline(evaluation.getTimeline())
                .evaluatorpriority((evaluation.getEvaluatorPriority()).name())
                .cost(evaluation.getCost()).approvedBy(user.getName())
                .approvedDate(new Date())
                .rfcDetailsName(evaluation.getRfcSupportApproveDetail().getRfcDetail().getProjectname())
                .build();
    }

    @Override
    public List<EvaluationResponse> convertAll(List<Evaluation> evaluations) {

        return null;
    }
}
