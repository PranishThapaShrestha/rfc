package com.nicasia.rfc.evaluation;

import com.nicasia.rfc.evaluation.dto.EvaluationRequest;
import com.nicasia.rfc.evaluation.dto.EvaluationResponse;
import com.nicasia.rfc.evaluation.service.EvaluationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class EvaluationController {

    private final EvaluationService evaluationService;

    public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping(value = "/rfcdetials/{id}/evaluations")
    public EvaluationResponse createEvaluation(
            @PathVariable(value = "id") Long id, @RequestBody EvaluationRequest evaluationRequest) {
        return evaluationService.createEvaluation(id, evaluationRequest);
    }

    @GetMapping(value = "/evaluations/{id}")
    public EvaluationResponse getEvaluationById(@PathVariable(value = "id") Long id) {
        return evaluationService.findEvaluationById(id);
    }

    @GetMapping(value = "/evaluations")
    public List<EvaluationResponse> fetchAllEvaluation() {
        return evaluationService.getAllEvaluation();
    }

    @PostMapping(value = "evaluations/{id}")
    public EvaluationResponse updateEvaluation(
            @PathVariable(value = "id") Long id, @RequestBody EvaluationRequest evaluationRequest) {
        return evaluationService.updateEvaluation(id, evaluationRequest);
    }


}
