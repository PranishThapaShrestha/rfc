package com.nicasia.rfc.evaluation.service;

import com.nicasia.rfc.core.usermanagement.user.entity.User;
import com.nicasia.rfc.core.usermanagement.user.service.UserConvert;
import com.nicasia.rfc.core.usermanagement.user.service.UserService;
import com.nicasia.rfc.evaluation.dto.EvaluationResponse;
import com.nicasia.rfc.evaluation.entity.Evaluation;
import com.nicasia.rfc.evaluation.entity.EvaluatorPriority;
import com.nicasia.rfc.evaluation.repo.EvaluationRepository;
import com.nicasia.rfc.rfcdetail.dto.PutRemarksDto;
import com.nicasia.rfc.rfcdetail.entity.RequestedForType;
import com.nicasia.rfc.rfcdetail.entity.RfcSupportApproveDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationServiceImpl implements EvaluationService {


    private final EvaluationRepository evaluationRepository;
    private final EvaluationConvert evaluationConvert;
    private final UserService userService;
    private final UserConvert userConvert;

    public EvaluationServiceImpl(EvaluationRepository evaluationRepository, EvaluationConvert evaluationConvert, UserService userService, UserConvert userConvert) {

        this.evaluationRepository = evaluationRepository;
        this.evaluationConvert = evaluationConvert;
        this.userService = userService;
        this.userConvert = userConvert;
    }

    @Override
    public Evaluation addEvaluation(PutRemarksDto putRemarksDto, RfcSupportApproveDetail rfcSupportApproveDetail) {

        Evaluation evaluation = new Evaluation();
        evaluation.setScope(putRemarksDto.getScope());
        evaluation.setCostresource(putRemarksDto.getCostresource());
        evaluation.setCost(putRemarksDto.getCost());
        evaluation.setRiskbasedoncia(putRemarksDto.getRiskbasedoncia());
        evaluation.setAlternativesandrecommendation(putRemarksDto.getAlternativesandrecommendation());
        evaluation.setRfcSupportApproveDetail(rfcSupportApproveDetail);
        evaluation.setEvaluatorPriority(EvaluatorPriority.valueOf(putRemarksDto.getPriority()));
        evaluation.setTimeline(putRemarksDto.getTimeline());
        return evaluationRepository.save(evaluation);
    }

    @Override
    public EvaluationResponse findEvaluationBasedOnRfcId(List<RfcSupportApproveDetail> rfcSupportApproveDetails) {

        RfcSupportApproveDetail rfcSupportApproveDetail1 = rfcSupportApproveDetails.stream()
                .filter(rfcSupportApproveDetail -> rfcSupportApproveDetail.getRequestedForType()
                        .equals(RequestedForType.APPROVE)).findFirst().get();
        Long id1 = rfcSupportApproveDetail1.getId();
        Long id = rfcSupportApproveDetail1.getUser().getId();
        User byId = userService.findById(id);

        Evaluation evaluation = evaluationRepository.findByRfcSupportApproveId(id1).get();




        return null;
    }


}


