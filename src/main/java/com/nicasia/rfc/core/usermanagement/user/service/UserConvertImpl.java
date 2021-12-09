package com.nicasia.rfc.core.usermanagement.user.service;

import com.nicasia.rfc.core.usermanagement.user.dto.UserMiniResource;
import com.nicasia.rfc.core.usermanagement.user.dto.UserResource;
import com.nicasia.rfc.core.usermanagement.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserConvertImpl implements UserConvert {

    @Override
    public UserResource convertToResource(User user) {
        return convertHelper(user);

    }

    private UserResource convertHelper(User user) {
        return UserResource.builder()
                .username(user.getUsername())
                .fullname(user.getName())
                .created(user.getCreatedAt())
                .department(user.getDepartment().getDeptname())
                .designation(user.getDesignation().getDesgname()).build();
    }

    @Override
    public List<UserResource> convertAllResource(List<User> users) {

        final List<UserResource> collect = users.stream().map(user -> convertHelper(user)).collect(Collectors.toList());

        return collect;
    }

    @Override
    public UserMiniResource convertToMiniResource(User user) {

        UserMiniResource.builder()
                .userId(user.getId())
                .userName(user.getName())
                .departmentCode(user.getDepartment().getDeptcode())
                .designationCode(user.getDesignation().getDesgcode())
                .fullName(user.getUsername()).build();

        return null;
    }
}
