package com.nicasia.rfc.core.usermanagement.user.service;

import com.nicasia.rfc.core.usermanagement.user.entity.User;
import com.nicasia.rfc.core.usermanagement.user.dto.UserMiniResource;
import com.nicasia.rfc.core.usermanagement.user.dto.UserResource;

import java.util.List;

public interface UserConvert {

    UserResource convertToResource(User user);

    List<UserResource> convertAllResource(List<User> users);

    UserMiniResource convertToMiniResource(User user);

    List<UserMiniResource> convertAllToMiniResource(List<User> users);

}


