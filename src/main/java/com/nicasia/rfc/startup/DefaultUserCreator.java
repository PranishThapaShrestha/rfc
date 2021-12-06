package com.nicasia.rfc.startup;

import com.nicasia.rfc.shared.enums.RoleName;
import com.nicasia.rfc.shared.enums.Status;
import com.nicasia.rfc.shared.exception.ResourceNotAvailableException;
import com.nicasia.rfc.usermanagement.department.entity.Department;
import com.nicasia.rfc.usermanagement.department.repo.DepartmentRepository;
import com.nicasia.rfc.usermanagement.designation.entity.Designation;
import com.nicasia.rfc.usermanagement.designation.repo.DesignationRepository;
import com.nicasia.rfc.usermanagement.user.entity.Roles;
import com.nicasia.rfc.usermanagement.user.entity.User;
import com.nicasia.rfc.usermanagement.user.repo.RoleRepository;
import com.nicasia.rfc.usermanagement.user.repo.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DefaultUserCreator {

    private final RoleRepository roleRepository;
    private final DepartmentRepository departmentRepository;
    private final DesignationRepository designationRepository;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    public DefaultUserCreator(RoleRepository roleRepository, DepartmentRepository departmentRepository, DesignationRepository designationRepository, PasswordEncoder encoder, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.departmentRepository = departmentRepository;
        this.designationRepository = designationRepository;
        this.encoder = encoder;
        this.userRepository = userRepository;
    }


    @PostConstruct
    public void init() {
        if (roleRepository.count() == 0) {
            addRoles();
        }
        if (userRepository.count() == 0) {
            createUser();
        }
    }


    @Transactional
    public void createUser() {
        Roles roles = roleRepository.finByName(RoleName.SUPER_ADMIN)
                .orElseThrow(() -> new ResourceNotAvailableException("Roles", "roles", RoleName.SUPER_ADMIN.name()));

        User user = new User();
        user.setName("Sisir Paudel");
        user.setUsername("SuperAdminSisir");
        user.setPassword(encoder.encode("12345"));
        user.setRoles(new HashSet<>(Collections.singletonList(roles)));
        user.setDepartment(getDepartment());
        user.setDesignation(getDesignation());
        userRepository.save(user);

    }

    private Designation getDesignation() {
        if (designationRepository.count() == 0) {
            Designation designation = new Designation();
            designation.setDesgcode("Designation1");
            designation.setDesgname("Supervisor");
            return designationRepository.save(designation);

        }
        return null;
    }

    private Department getDepartment() {
        if (departmentRepository.count() == 0) {
            Department department = new Department();
            department.setStatus(Status.ACTIVE);
            department.setDeptcode("D1");
            department.setDeptname("IT");
            return departmentRepository.save(department);

        }
        return null;
    }


    @Transactional
    public void addRoles() {
        List<Roles> roles = Arrays.stream(RoleName.values())
                .map(roleName -> getRoles(roleName)).collect(Collectors.toList());
        roleRepository.saveAll(roles);

    }

    private Roles getRoles(RoleName roleName) {
        Roles roles = new Roles();
        roles.setName(roleName);
        return roles;
    }


}
