package com.nicasia.rfc.core.usermanagement.department.service;

import com.nicasia.rfc.core.usermanagement.department.dto.DepartmentRequest;
import com.nicasia.rfc.core.usermanagement.department.dto.DepartmentResource;
import com.nicasia.rfc.core.usermanagement.department.entity.Department;

import java.util.List;

public interface DepartmentService {

    Department findById(Long id);

    DepartmentResource addNewDepartment(DepartmentRequest departmentRequest);

    List<DepartmentResource> getAllDepartments();

    DepartmentResource removeDepartment(Long id);

    List<DepartmentResource> findAllDepartmentBasedOnStatus();

    DepartmentResource updateDepartmentDetail(Long id,DepartmentRequest departmentRequest);

    DepartmentResource updateDepartmentStatus(Long id, String status);
}
