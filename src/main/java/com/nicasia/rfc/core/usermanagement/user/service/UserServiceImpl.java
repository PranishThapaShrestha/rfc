package com.nicasia.rfc.core.usermanagement.user.service;

import com.nicasia.rfc.core.usermanagement.department.service.DepartmentService;
import com.nicasia.rfc.core.usermanagement.user.entity.Roles;
import com.nicasia.rfc.core.usermanagement.user.entity.User;
import com.nicasia.rfc.core.usermanagement.user.repo.RoleRepository;
import com.nicasia.rfc.core.usermanagement.user.repo.UserRepository;
import com.nicasia.rfc.shared.enums.RoleName;
import com.nicasia.rfc.shared.enums.Status;
import com.nicasia.rfc.shared.exception.ClientException;
import com.nicasia.rfc.shared.exception.ResourceNotAvailableException;
import com.nicasia.rfc.shared.succesresponse.SuccessResponse;
import com.nicasia.rfc.core.usermanagement.designation.service.DesignationService;
import com.nicasia.rfc.core.usermanagement.user.dto.CreateUserRequest;
import com.nicasia.rfc.core.usermanagement.user.dto.UserResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DepartmentService departmentService;
    private final DesignationService designationService;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserConvert userConvert;

    public UserServiceImpl(UserRepository userRepository,
                           DepartmentService departmentService,
                           DesignationService designationService,
                           PasswordEncoder passwordEncoder,
                           RoleRepository roleRepository, UserConvert userConvert) {
        this.userRepository = userRepository;
        this.departmentService = departmentService;
        this.designationService = designationService;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userConvert = userConvert;
    }

    @Override
    public SuccessResponse createUser(CreateUserRequest createUserRequest) {

        Optional<User> existingUser = userRepository.findByUserName(createUserRequest.getUsername());
        if (existingUser.isPresent()) {
            throw new ClientException("User already exists");
        }
        User user = new User();
        addUserIfNotPresent(user, createUserRequest);

        return SuccessResponse.builder().successMessage("User successfully created").build();
    }

    private void addUserIfNotPresent(User user, CreateUserRequest createUserRequest) {
        user.setName(createUserRequest.getName());
        user.setUsername(createUserRequest.getUsername());
        user.setDepartment(departmentService.findById(createUserRequest.getDepartmentId()));
        user.setDesignation(designationService.findById(createUserRequest.getDesignationId()));
        user.setPassword(passwordEncoder.encode("password"));
        Roles roles = roleRepository.findByName(RoleName.MODERATOR).orElseThrow
                (() -> new ResourceNotAvailableException("Role", "rolename", RoleName.MODERATOR.name()));
        user.setRoles(new HashSet<>(Collections.singletonList(roles)));
        user.setStatus(Status.ACTIVE);
        userRepository.save(user);
    }

    @Override
    public List<UserResource> getAllUsers() {
        return userConvert.convertAllResource((List<User>) userRepository.findAll());
    }

    @Override
    public UserResource findUserResourceById(Long id) {
        final User user = userRepository.findById(id).orElseThrow
                (() -> new ResourceNotAvailableException("User", "id", id));

        return userConvert.convertToResource(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByName(String name) {
        return userRepository.findByUserName(name);
    }


}

