package com.nicasia.rfc.usermanagement.designation;

import com.nicasia.rfc.usermanagement.designation.dto.DesignationRequest;
import com.nicasia.rfc.usermanagement.designation.dto.DesignationResource;
import com.nicasia.rfc.usermanagement.designation.service.DesignationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/designation")
public class DesignationController {

    private final DesignationService designationService;

    public DesignationController(DesignationService designationService) {
        this.designationService = designationService;
    }

    @GetMapping(value = "/all-designations")
    public List<DesignationResource> getAllDesignations() {
        return designationService.findAllDesignation();
    }

    @PostMapping(value = "/add")
    public DesignationResource createDesignation(@RequestBody DesignationRequest designationRequest) {
        return designationService.addNewDesignation(designationRequest);
    }

    @PostMapping(value = "/edit/{id}")
    public DesignationResource updateDesignation(@PathVariable(value = "id") Long id,
                                                 @RequestBody DesignationRequest designationRequest) {

        return designationService.updateDesignation(id, designationRequest);
    }


}
