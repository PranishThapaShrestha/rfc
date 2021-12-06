package com.nicasia.rfc.usermanagement.department.service;

import com.nicasia.rfc.shared.enums.Status;
import com.nicasia.rfc.shared.exception.ResourceNotAvailableException;
import com.nicasia.rfc.usermanagement.department.dto.DepartmentRequest;
import com.nicasia.rfc.usermanagement.department.dto.DepartmentResource;
import com.nicasia.rfc.usermanagement.department.entity.Department;
import com.nicasia.rfc.usermanagement.department.repo.DepartmentRepository;
import org.springframework.stereotype.Service;
import com.nicasia.rfc.exception.*;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    private final DepartmentConvert departmentConvert;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, DepartmentConvert departmentConvert) {
        this.departmentRepository = departmentRepository;
        this.departmentConvert = departmentConvert;
    }

    @Override
    public Department findById(Long departmentId) {
        return departmentRepository.findById(departmentId).orElseThrow(
                () -> new ResourceNotAvailableException("Department detail", "departmentId", departmentId)
        );
    }

    @Override
    public DepartmentResource addNewDepartment(DepartmentRequest departmentRequest) {
        Department department1 = new Department();
        department1.setStatus(Status.ACTIVE);
        department1.setDeptname(departmentRequest.getName());
        department1.setDeptcode(departmentRequest.getCode());


        return departmentConvert.convert(departmentRepository.save(department1));
    }

    @Override
    public List<DepartmentResource> getAllDepartments() {
        final Iterable<Department> all = departmentRepository.findAll();

        return departmentConvert.convertAll((List<Department>) all);
    }

    @Override
    public DepartmentResource removeDepartment(Long departmentId) {
        final Department department = departmentRepository.findById(departmentId).orElseThrow(
                () -> new ResourceNotAvailableException("Resource you are trying to delete", "departmentId", departmentId));
        department.setStatus(Status.DELETED);
        return departmentConvert.convert(departmentRepository.save(department));
    }

    @Override
    public List<DepartmentResource> findAllDepartmentBasedOnStatus() {

        final List<DepartmentResource> departmentResources = departmentConvert.
                convertAll(departmentRepository.getAllDepartmentsBasedStatus(Status.ACTIVE));
        return departmentResources;
    }

    @Override
    public DepartmentResource updateDepartmentStatus(Long departmentId, Status status) {

        Department department = departmentRepository.findById(departmentId).orElseThrow(
                () -> new ResourceNotAvailableException("Department you are looking of", "departmentId", departmentId));
        department.setStatus(Status.valueOf(String.valueOf(status)));
        final Department department1 = departmentRepository.save(department);
        return departmentConvert.convert(department1);
    }
}
