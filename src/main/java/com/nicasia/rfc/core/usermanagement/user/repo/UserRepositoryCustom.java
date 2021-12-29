package com.nicasia.rfc.core.usermanagement.user.repo;

import com.nicasia.rfc.core.usermanagement.user.entity.User;
import com.nicasia.rfc.shared.abstracts.BaseRepositoryCustom;
import com.nicasia.rfc.shared.enums.Status;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryCustom extends BaseRepositoryCustom<User> {

    Optional<User> findByUserName(String userName);

    List<User> findAllUserBasedOnStatus(Status status);

    List<User> searchUser(String userName);

    List<User> findAllUserByIds(List<Long> userIds);
}
