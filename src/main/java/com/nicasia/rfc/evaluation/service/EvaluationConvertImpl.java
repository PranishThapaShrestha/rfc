package com.nicasia.rfc.evaluation.service;

import com.nicasia.rfc.evaluation.dto.EvaluationResponse;
import com.nicasia.rfc.evaluation.entity.Evaluation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationConvertImpl implements EvaluationConvert {
    @Override
    public EvaluationResponse convertOne(Evaluation evaluation) {
        return null;
    }

    @Override
    public List<EvaluationResponse> convertAll(List<Evaluation> evaluations) {
        return null;
    }
}
