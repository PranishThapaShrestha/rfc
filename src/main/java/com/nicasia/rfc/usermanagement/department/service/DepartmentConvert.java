package com.nicasia.rfc.usermanagement.department.service;

import com.nicasia.rfc.usermanagement.department.dto.DepartmentResource;
import com.nicasia.rfc.usermanagement.department.entity.Department;

import java.util.List;

public interface DepartmentConvert {

    DepartmentResource convert(Department department);

    List<DepartmentResource> convertAll(List<Department> allDepartment);

}
