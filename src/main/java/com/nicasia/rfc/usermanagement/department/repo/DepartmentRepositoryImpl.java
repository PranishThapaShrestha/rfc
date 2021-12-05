package com.nicasia.rfc.usermanagement.department.repo;

import com.nicasia.rfc.abstracts.BaseRepositoryImpl;
import com.nicasia.rfc.enums.Status;
import com.nicasia.rfc.usermanagement.department.entity.Department;
import com.nicasia.rfc.usermanagement.department.entity.QDepartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.List;
import java.util.Optional;

public class DepartmentRepositoryImpl extends BaseRepositoryImpl<Department, DepartmentRepository>
        implements DepartmentRepositoryCustom {


    QDepartment department = QDepartment.department;

    public DepartmentRepositoryImpl() {
        super(Department.class);
    }

    @Lazy
    @Autowired
    public void setRepository(DepartmentRepository departmentRepository) {
        this.repository = departmentRepository;
    }


    @Override
    public Optional<Department> findByDepartmentCode(String code) {

        return repository.findOne(department.deptcode.eq(code));
    }

    @Override
    public List<Department> getAllDepartmentsBasedStatus(Status status) {
        return (List<Department>) repository.findAll(department.status.eq(status));
    }
}
