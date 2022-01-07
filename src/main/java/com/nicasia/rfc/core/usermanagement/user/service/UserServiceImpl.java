package com.nicasia.rfc.core.usermanagement.user.service;

import com.nicasia.rfc.core.usermanagement.department.service.DepartmentService;
import com.nicasia.rfc.core.usermanagement.designation.service.DesignationService;
import com.nicasia.rfc.core.usermanagement.user.dto.CreateUserRequest;
import com.nicasia.rfc.core.usermanagement.user.dto.UserMiniResource;
import com.nicasia.rfc.core.usermanagement.user.dto.UserResource;
import com.nicasia.rfc.core.usermanagement.user.entity.Roles;
import com.nicasia.rfc.core.usermanagement.user.entity.User;
import com.nicasia.rfc.core.usermanagement.user.repo.RoleRepository;
import com.nicasia.rfc.core.usermanagement.user.repo.UserRepository;
import com.nicasia.rfc.shared.enums.RoleName;
import com.nicasia.rfc.shared.enums.Status;
import com.nicasia.rfc.shared.exception.ClientException;
import com.nicasia.rfc.shared.exception.ResourceNotAvailableException;
import com.nicasia.rfc.shared.succesresponse.SuccessResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
        user.setEmail(createUserRequest.getEmail().trim());
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
    public Map<Long, UserMiniResource> findUserMiniResourceByUserIds(List<Long> userIds) {

       return userConvert.convertAllToMiniResource(userRepository.findAllUserByIds(userIds))
                .stream().collect(Collectors.toMap(userMiniResource -> userMiniResource.getUserId(),o -> o));
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
    public List<User> findAllUserByIdsIn(List<Long> ids) {
        return userRepository.findAllUserByIds(ids);
    }


    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotAvailableException("user of id ::", "id", id));
    }

    @Override
    public Optional<User> findByName(String name) {
        return userRepository.findByUserName(name);
    }

    @Override
    public SuccessResponse updateUser(Long id, CreateUserRequest createUserRequest) {

        Roles roles = roleRepository.findByName(RoleName.MODERATOR)
                .orElseThrow(() -> new ResourceNotAvailableException("Roles with name ::", "id", RoleName.MODERATOR));

        Optional<User> user = userRepository.findByUserName(createUserRequest.getUsername().trim());
        if (user.isPresent() && !user.get().getId().equals(id)) {
            throw new ClientException("Client already exists");
        }
        User users = userRepository.findById(id).orElseThrow(() -> new ResourceNotAvailableException("User of id::", "id", id));
        users.setName(createUserRequest.getName());
        users.setUsername(createUserRequest.getUsername());
        users.setPassword(passwordEncoder.encode("123456"));
        users.setStatus(Status.ACTIVE);
        users.setRoles(new HashSet<>(Arrays.asList(roles)));
        users.setDesignation(designationService.findById(createUserRequest.getDepartmentId()));
        users.setDepartment(departmentService.findById(createUserRequest.getDepartmentId()));
        userRepository.save(users);
        return SuccessResponse.builder().successMessage("user successfully updated").build();


    }
}


//        Optional<User> user = userRepository.findByUserName(createUserRequest.getUsername().trim());
//        if (user.isPresent() && !user.get().getId().equals(id)) {
//            throw new ClientException("Client already exist");
//        }
//        User user1 = userRepository.findById(id).orElseThrow(
//                () -> new ResourceNotAvailableException("User with id ::", "id", id));
//        user1.setName(createUserRequest.getName());
//        user1.setUsername(createUserRequest.getUsername());
//        user1.setDepartment(departmentService.findById(createUserRequest.getDepartmentId()));
//        user1.setDesignation(designationService.findById(createUserRequest.getDesignationId()));
//        user1.setPassword(passwordEncoder.encode("12345"));
//        user1.setStatus(Status.ACTIVE);
//        Roles roles = roleRepository.findByName(RoleName.MODERATOR)
//                .orElseThrow(() -> new ResourceNotAvailableException("Roles of name ::", "roles", RoleName.MODERATOR));
//
//        user1.setRoles(new HashSet<>(Arrays.asList(roles)));
//        userRepository.save(user1);
//
//        return SuccessResponse.builder().successMessage("Update has been done").build();
//        }
//}


//    @Override
//    public SuccessResponse updateUser(Long id, CreateUserRequest createUserRequest) {
//        Optional<User> user = userRepository.findByUserName(createUserRequest.getUsername().trim());
//        if (user.isPresent() && !Objects.equals(user.get().getId(), id)) {
//            throw new ClientException("client already exist");
//        }
//        addUserIfNotPresent( findById(id), createUserRequest);
//        return SuccessResponse.builder().successMessage("Successfully updated the user").build();
//    }
//}

