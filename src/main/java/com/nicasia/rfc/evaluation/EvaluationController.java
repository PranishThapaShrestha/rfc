package com.nicasia.rfc.evaluation;

import com.nicasia.rfc.evaluation.dto.EvaluationRequest;
import com.nicasia.rfc.evaluation.dto.EvaluationResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/evaluation")
public class EvaluationController {

    public EvaluationResponse createEvaluation(
            @PathVariable(value = "id")Long id, @RequestBody EvaluationRequest evaluationRequest){
        return null;
    }

}
