package com.nicasia.rfc.core.usermanagement.department;

import com.nicasia.rfc.core.usermanagement.department.dto.DepartmentRequest;
import com.nicasia.rfc.core.usermanagement.department.dto.DepartmentResource;
import com.nicasia.rfc.core.usermanagement.department.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping(value = "/departments")
    public DepartmentResource addDepartment(@RequestBody DepartmentRequest departmentRequest) {
        return departmentService.addNewDepartment(departmentRequest);
    }

    @GetMapping(value = "/departments")
    public List<DepartmentResource> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @PostMapping(value = "/departments/{id}/remove")
    public DepartmentResource removeDepartment(@PathVariable(value = "id") Long departmentId) {
        return departmentService.removeDepartment(departmentId);
    }

    @PostMapping(value = "/departments/{id}/{status}/update")
    public DepartmentResource updateDepartmentStatus(@PathVariable(value = "id") Long id,
                                                     @PathVariable String status) {
        return departmentService.updateDepartmentStatus(id, status);
    }

    @PostMapping(value = "/departments/{id}/update")
    public DepartmentResource updateDepartmentDetail(@PathVariable(value = "id") Long id,
                                                     @RequestBody DepartmentRequest departmentRequest) {
        return departmentService.updateDepartmentDetail(id, departmentRequest);
    }

}
