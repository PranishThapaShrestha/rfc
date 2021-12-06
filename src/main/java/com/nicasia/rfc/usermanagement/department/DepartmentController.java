package com.nicasia.rfc.usermanagement.department;

import com.nicasia.rfc.shared.enums.Status;
import com.nicasia.rfc.usermanagement.department.dto.DepartmentRequest;
import com.nicasia.rfc.usermanagement.department.dto.DepartmentResource;
import com.nicasia.rfc.usermanagement.department.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping(value = "/add")
    public DepartmentResource createDepartment(@RequestBody DepartmentRequest departmentRequest) {
        return departmentService.addNewDepartment(departmentRequest);
    }

    @GetMapping(value = "/alldepartments")
    public List<DepartmentResource> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @PutMapping(value = "/{department_id}/remove")
    public DepartmentResource removeDepartment(@PathVariable(value = "depatment_id") Long departmentId) {
        return departmentService.removeDepartment(departmentId);
    }

    @PostMapping(value = "{department_id}/{status}")
    public DepartmentResource updateDepartmentStatus(@PathVariable(value = "department_id") Long departmentId,
                                                     @PathVariable(value = "status") Status status) {
        return departmentService.updateDepartmentStatus(departmentId, status);
    }
}
