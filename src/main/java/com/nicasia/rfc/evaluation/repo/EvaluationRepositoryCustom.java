package com.nicasia.rfc.evaluation.repo;

import com.nicasia.rfc.evaluation.entity.Evaluation;
import com.nicasia.rfc.shared.abstracts.BaseRepositoryCustom;

import java.util.Optional;

public interface EvaluationRepositoryCustom extends BaseRepositoryCustom<Evaluation> {

    Optional<Evaluation> findByRfcSupportApproveId(Long rfcSupportApproveId);

}
