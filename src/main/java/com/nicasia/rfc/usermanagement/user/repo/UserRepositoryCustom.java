package com.nicasia.rfc.usermanagement.user.repo;

import com.nicasia.rfc.shared.abstracts.BaseRepositoryCustom;
import com.nicasia.rfc.shared.enums.Status;
import com.nicasia.rfc.usermanagement.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryCustom extends BaseRepositoryCustom<User> {

    Optional<User> findByUserName(String userName);

    List<User> findAllUserBasedOnStatus(Status status);

    List<User> searchUser(String userName);
}
