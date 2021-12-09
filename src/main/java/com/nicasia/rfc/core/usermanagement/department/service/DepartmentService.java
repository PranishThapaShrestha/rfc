package com.nicasia.rfc.core.usermanagement.department.service;

import com.nicasia.rfc.shared.enums.Status;
import com.nicasia.rfc.core.usermanagement.department.dto.DepartmentRequest;
import com.nicasia.rfc.core.usermanagement.department.dto.DepartmentResource;
import com.nicasia.rfc.core.usermanagement.department.entity.Department;

import java.util.List;

public interface DepartmentService {

    Department findById(Long departmentId);

    DepartmentResource addNewDepartment(DepartmentRequest departmentRequest);

    List<DepartmentResource> getAllDepartments();

    DepartmentResource removeDepartment(Long departmentId);

    List<DepartmentResource> findAllDepartmentBasedOnStatus();

    DepartmentResource updateDepartmentStatus(Long departmentId, Status status);
}
