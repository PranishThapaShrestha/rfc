package com.nicasia.rfc.usermanagement.department.service;

import com.nicasia.rfc.enums.Status;
import com.nicasia.rfc.usermanagement.department.dto.DepartmentRequest;
import com.nicasia.rfc.usermanagement.department.dto.DepartmentResource;
import com.nicasia.rfc.usermanagement.department.entity.Department;

import java.util.List;

public interface DepartmentService {

    Department findById(Long departmentId);

    DepartmentResource addNewDepartment(DepartmentRequest departmentRequest);

    List<DepartmentResource> getAllDepartments();

    DepartmentResource removeDepartment(Long departmentId);

    List<DepartmentResource> findAllDepartmentBasedOnStatus();

    DepartmentResource updateDepartmentStatus(Long departmentId, Status status);
}
