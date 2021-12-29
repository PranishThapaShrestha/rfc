package com.nicasia.rfc.core.usermanagement.department.service;

import com.nicasia.rfc.core.usermanagement.department.repo.DepartmentRepository;
import com.nicasia.rfc.shared.enums.Status;
import com.nicasia.rfc.shared.exception.ResourceNotAvailableException;
import com.nicasia.rfc.core.usermanagement.department.dto.DepartmentRequest;
import com.nicasia.rfc.core.usermanagement.department.dto.DepartmentResource;
import com.nicasia.rfc.core.usermanagement.department.entity.Department;
import org.springframework.stereotype.Service;

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
    public Department findById(Long id) {
        return departmentRepository.findById(id).orElseThrow(
                () -> new ResourceNotAvailableException("Department detail", "departmentId", id)
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
    public DepartmentResource removeDepartment(Long id) {
        final Department department = departmentRepository.findById(id).orElseThrow(
                () -> new ResourceNotAvailableException("Resource you are trying to delete ::", "departmentId", id));
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
    public DepartmentResource updateDepartmentStatus(Long id, String status) {

        Department department = departmentRepository.findById(id).orElseThrow(
                () -> new ResourceNotAvailableException("Department you are looking of", "departmentId", id));
        department.setStatus(Status.valueOf(status));
        final Department department1 = departmentRepository.save(department);
        return departmentConvert.convert(department1);
    }

    @Override
    public DepartmentResource updateDepartmentDetail(Long id, DepartmentRequest departmentRequest) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotAvailableException("Department of id ::", "id", id));
        department.setDeptcode(departmentRequest.getCode());
        department.setDeptname(departmentRequest.getName());
        return departmentConvert.convert(departmentRepository.save(department));
    }
}
