package com.nicasia.rfc.evaluation.service;

import com.nicasia.rfc.evaluation.dto.EvaluationRequest;
import com.nicasia.rfc.evaluation.dto.EvaluationResponse;
import com.nicasia.rfc.evaluation.entity.Evaluation;
import com.nicasia.rfc.evaluation.repo.EvaluationRepository;
import com.nicasia.rfc.rfcdetail.entity.RfcDetail;
import com.nicasia.rfc.rfcdetail.service.RfcDetailService;
import com.nicasia.rfc.shared.exception.ResourceNotAvailableException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationServiceImpl implements EvaluationService {

    private final RfcDetailService rfcDetailService;
    private final EvaluationRepository evaluationRepository;
    private final EvaluationConvert evaluationConvert;

    public EvaluationServiceImpl(RfcDetailService rfcDetailService, EvaluationRepository evaluationRepository, EvaluationConvert evaluationConvert) {
        this.rfcDetailService = rfcDetailService;
        this.evaluationRepository = evaluationRepository;
        this.evaluationConvert = evaluationConvert;
    }

    @Override
    public EvaluationResponse createEvaluation(Long id, EvaluationRequest evaluationRequest) {

        RfcDetail rfcDetail = rfcDetailService.findById(id);
        Evaluation evaluation = new Evaluation();
        evaluation.setRfcdetail(rfcDetail);
        evaluation.setScope(evaluationRequest.getScope());
        evaluation.setCost(evaluationRequest.getCost());
        evaluation.setCostresource(evaluationRequest.getCostresource());
        evaluation.setTimeline(evaluationRequest.getTimeline());
        evaluation.setEvaluatorPriority(evaluationRequest.getPriority().name());
        evaluation.setRiskbasedoncia(evaluationRequest.getRiskbasedoncia());
        evaluation.setAlternativesandrecommendation(evaluationRequest.getAlternativesandrecommendation());
        Evaluation save = evaluationRepository.save(evaluation);
        return evaluationConvert.convertOne(save);
    }

    @Override
    public EvaluationResponse findEvaluationById(Long id) {
        Evaluation evaluation = evaluationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotAvailableException("Evaluation of id ::", "", id));
        return evaluationConvert.convertOne(evaluation);
    }

    @Override
    public List<EvaluationResponse> getAllEvaluation() {
        final Iterable<Evaluation> all = evaluationRepository.findAll();
        final List<EvaluationResponse> evaluationResponses = evaluationConvert.convertAll((List<Evaluation>) all);
        return evaluationResponses;
    }

    @Override
    public EvaluationResponse updateEvaluation(Long id, EvaluationRequest evaluationRequest) {
        Evaluation evaluation = evaluationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotAvailableException("Evaluation of id ::", "", id));
        evaluation.setCost(evaluationRequest.getCost());
        evaluation.setTimeline(evaluationRequest.getTimeline());
        evaluation.setRiskbasedoncia(evaluationRequest.getRiskbasedoncia());
        evaluation.setCostresource(evaluationRequest.getCostresource());
        evaluation.setScope(evaluationRequest.getScope());
        evaluation.setEvaluatorPriority(evaluationRequest.getPriority().name());
        return evaluationConvert.convertOne(evaluationRepository.save(evaluation));
    }
}
