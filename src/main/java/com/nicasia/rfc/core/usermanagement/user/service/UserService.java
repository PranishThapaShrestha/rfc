package com.nicasia.rfc.core.usermanagement.user.service;


import com.nicasia.rfc.shared.succesresponse.SuccessResponse;
import com.nicasia.rfc.core.usermanagement.user.dto.CreateUserRequest;
import com.nicasia.rfc.core.usermanagement.user.dto.UserResource;
import com.nicasia.rfc.core.usermanagement.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    SuccessResponse createUser(CreateUserRequest createUserRequest);

    List<UserResource> getAllUsers();

    UserResource findUserResourceById(Long id);

    Optional<User> findById(Long id);


    Optional<User> findByName(String name);

}
