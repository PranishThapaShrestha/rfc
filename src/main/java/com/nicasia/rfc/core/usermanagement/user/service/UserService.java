package com.nicasia.rfc.core.usermanagement.user.service;


import com.nicasia.rfc.core.usermanagement.user.dto.CreateUserRequest;
import com.nicasia.rfc.core.usermanagement.user.dto.UserMiniResource;
import com.nicasia.rfc.core.usermanagement.user.dto.UserResource;
import com.nicasia.rfc.core.usermanagement.user.entity.User;
import com.nicasia.rfc.shared.succesresponse.SuccessResponse;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {

    SuccessResponse createUser(CreateUserRequest createUserRequest);

    List<UserResource> getAllUsers();

    UserResource findUserResourceById(Long id);

    List<User> findAllUserByIdsIn(List<Long> ids);

    Map<Long, UserMiniResource> findUserMiniResourceByUserIds(List<Long> userIds);

    User findById(Long id);

    Optional<User> findByName(String name);

    SuccessResponse updateUser(Long id,CreateUserRequest createUserRequest);
}
