package com.nicasia.rfc.startup;

import com.nicasia.rfc.core.usermanagement.department.entity.Department;
import com.nicasia.rfc.core.usermanagement.department.repo.DepartmentRepository;
import com.nicasia.rfc.core.usermanagement.designation.entity.Designation;
import com.nicasia.rfc.core.usermanagement.designation.repo.DesignationRepository;
import com.nicasia.rfc.core.usermanagement.user.entity.Roles;
import com.nicasia.rfc.core.usermanagement.user.entity.User;
import com.nicasia.rfc.core.usermanagement.user.repo.RoleRepository;
import com.nicasia.rfc.core.usermanagement.user.repo.UserRepository;
import com.nicasia.rfc.shared.enums.RoleName;
import com.nicasia.rfc.shared.enums.Status;
import com.nicasia.rfc.shared.exception.ResourceNotAvailableException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static com.nicasia.rfc.shared.enums.RoleName.*;

@Component
public class DefaultUserCreator {

    private final RoleRepository roleRepository;
    private final DepartmentRepository departmentRepository;
    private final DesignationRepository designationRepository;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    public DefaultUserCreator(RoleRepository roleRepository, DepartmentRepository departmentRepository,
                              DesignationRepository designationRepository, UserRepository userRepository,
                              PasswordEncoder encoder) {
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
        Roles roles = roleRepository.findByName(RoleName.SUPER_ADMIN)
                .orElseThrow(() -> new ResourceNotAvailableException("Roles", "roles", SUPER_ADMIN.name()));

        User user = new User();
        user.setName("Sisir Paudel");
        user.setUsername("Sisir");
        user.setPassword(encoder.encode("12345"));
        user.setRoles(new HashSet<>(Collections.singletonList(roles)));
        user.setDepartment(getDepartment());
        user.setDesignation(getDesignation());
        user.setStatus(Status.ACTIVE);
        userRepository.save(user);

    }

    private Designation getDesignation() {
        if (designationRepository.count() == 0) {
            Designation designation = new Designation();
            designation.setDesgcode("00O1");
            designation.setDesgname("Officer");
            return designationRepository.save(designation);

        }
        return null;
    }

    private Department getDepartment() {
        if (departmentRepository.count() == 0) {
            Department department = new Department();
            department.setStatus(Status.ACTIVE);
            department.setDeptcode("00H1");
            department.setDeptname("Head Office");
            return departmentRepository.save(department);

        }
        return null;
    }
//    @Transactional
//    public void addRoles() {
//        List<Roles> roles = Arrays.stream(values()).map(roleName -> getRoles(roleName))
//                .collect(Collectors.toList());
//        roleRepository.saveAll(roles);
//    }
//
//
//    private Roles getRoles(RoleName roleName) {
//        Roles roles = new Roles();
//        roles.setName(roleName);
//        return roles;
//    }


    @Transactional
    public void addRoles() {
        final List<Roles> roles = Arrays.stream(RoleName.values())
                .map(roleName -> getRoles(roleName)).collect(Collectors.toList());
        roleRepository.saveAll(roles);
    }

    private Roles getRoles(RoleName roleName) {
        Roles roles = new Roles();
        roles.setName(roleName);
        return roles;
    }

}
