package com.nicasia.rfc.usermanagement.user.service;


import com.nicasia.rfc.succesresponse.SuccessResponse;
import com.nicasia.rfc.usermanagement.user.dto.CreateUserRequest;
import com.nicasia.rfc.usermanagement.user.dto.UserResource;

import java.util.List;

public interface UserService {

    SuccessResponse createUser(CreateUserRequest createUserRequest);

    List<UserResource> getAllUsers();

    UserResource findById(Long id);

}
