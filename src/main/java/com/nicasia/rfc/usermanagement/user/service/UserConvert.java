package com.nicasia.rfc.usermanagement.user.service;

import com.nicasia.rfc.usermanagement.user.dto.UserMiniResource;
import com.nicasia.rfc.usermanagement.user.dto.UserResource;
import com.nicasia.rfc.usermanagement.user.entity.User;

import java.util.List;

public interface UserConvert {

    UserResource convertToResource(User user);

    List<UserResource> convertAllResource(List<User> users);

    UserMiniResource convertToMiniResource(User user);

}


