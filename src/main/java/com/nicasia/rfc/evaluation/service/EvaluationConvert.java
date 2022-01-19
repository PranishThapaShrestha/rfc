package com.nicasia.rfc.evaluation.service;

import com.nicasia.rfc.core.usermanagement.user.entity.User;
import com.nicasia.rfc.evaluation.dto.EvaluationResponse;
import com.nicasia.rfc.evaluation.entity.Evaluation;

import java.util.List;

public interface EvaluationConvert {

    EvaluationResponse convertOne(Evaluation evaluation, User user);

    List<EvaluationResponse> convertAll(List<Evaluation> evaluations);
}
