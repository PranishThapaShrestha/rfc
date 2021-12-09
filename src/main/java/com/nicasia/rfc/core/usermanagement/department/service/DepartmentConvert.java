package com.nicasia.rfc.core.usermanagement.department.service;

import com.nicasia.rfc.core.usermanagement.department.dto.DepartmentResource;
import com.nicasia.rfc.core.usermanagement.department.entity.Department;

import java.util.List;

public interface DepartmentConvert {

    DepartmentResource convert(Department department);

    List<DepartmentResource> convertAll(List<Department> allDepartment);

}
