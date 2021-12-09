package com.nicasia.rfc.core.usermanagement.department.repo;

import com.nicasia.rfc.core.usermanagement.department.entity.Department;
import com.nicasia.rfc.shared.abstracts.BaseRepositoryCustom;
import com.nicasia.rfc.shared.enums.Status;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepositoryCustom extends BaseRepositoryCustom<Department> {

    Optional<Department> findByDepartmentCode(String code);

    List<Department> getAllDepartmentsBasedStatus(Status status);
}
