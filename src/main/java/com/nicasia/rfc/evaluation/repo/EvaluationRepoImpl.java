package com.nicasia.rfc.evaluation.repo;

import com.nicasia.rfc.evaluation.entity.Evaluation;
import com.nicasia.rfc.evaluation.entity.QEvaluation;
import com.nicasia.rfc.shared.abstracts.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.Optional;

public class EvaluationRepoImpl extends BaseRepositoryImpl<Evaluation, EvaluationRepository>
        implements EvaluationRepositoryCustom {

    QEvaluation qEvaluation = QEvaluation.evaluation;

    public EvaluationRepoImpl() {
        super(Evaluation.class);
    }

    @Lazy
    @Autowired
    public void setRepository(EvaluationRepository evaluationRepository) {

        this.repository = evaluationRepository;
    }

    @Override
    public Optional<Evaluation> findByRfcSupportApproveId(Long rfcSupportApproveId) {
        return repository.findOne(qEvaluation.rfcSupportApproveDetail.id.eq(rfcSupportApproveId));


    }
}
