package com.nicasia.rfc.core.usermanagement.department;

import com.nicasia.rfc.shared.enums.Status;
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
    public DepartmentResource createDepartment(@RequestBody DepartmentRequest departmentRequest) {
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
    public DepartmentResource updateDepartmentStatus(@PathVariable(value = "department_id") Long departmentId,
                                                     @PathVariable(value = "status") Status status) {
        return departmentService.updateDepartmentStatus(departmentId, status);
    }
}
