package com.nicasia.rfc.evaluation.repo;

import com.nicasia.rfc.evaluation.entity.Evaluation;
import com.nicasia.rfc.evaluation.entity.QEvaluation;
import com.nicasia.rfc.shared.abstracts.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

public class EvaluationRepoImpl extends BaseRepositoryImpl<Evaluation, EvaluationRepository> {

    QEvaluation qEvaluation = QEvaluation.evaluation;

    public EvaluationRepoImpl() {
        super(Evaluation.class);
    }

    @Lazy
    @Autowired
    public void setRepository(EvaluationRepository evaluationRepository) {
        this.repository = evaluationRepository;
    }
}
