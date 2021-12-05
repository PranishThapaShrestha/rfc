package com.nicasia.rfc.usermanagement.department.repo;

import com.nicasia.rfc.abstracts.BaseRepositoryCustom;
import com.nicasia.rfc.enums.Status;
import com.nicasia.rfc.usermanagement.department.entity.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepositoryCustom extends BaseRepositoryCustom<Department> {

    Optional<Department> findByDepartmentCode(String code);

    List<Department> getAllDepartmentsBasedStatus(Status status);
}
