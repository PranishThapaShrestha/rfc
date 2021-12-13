package com.nicasia.rfc.core.usermanagement.department.service;

import com.nicasia.rfc.core.usermanagement.department.dto.DepartmentResource;
import com.nicasia.rfc.core.usermanagement.department.entity.Department;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentConvertImpl implements DepartmentConvert {
    @Override
    public DepartmentResource convert(Department department) {
        return convertDepartment(department);
    }

    private DepartmentResource convertDepartment(Department department) {
        return DepartmentResource.builder()
                .id(department.getId())
                .name(department.getDeptname())
                .code(department.getDeptcode())
                .created(department.getCreatedAt()).build();
    }

    @Override
    public List<DepartmentResource> convertAll(List<Department> allDepartment) {
        return allDepartment.stream().map(this::convertDepartment).collect(Collectors.toList());

    }
}
