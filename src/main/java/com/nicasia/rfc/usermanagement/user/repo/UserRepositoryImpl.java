package com.nicasia.rfc.usermanagement.user.repo;

import com.nicasia.rfc.abstracts.BaseRepositoryImpl;
import com.nicasia.rfc.enums.Status;
import com.nicasia.rfc.usermanagement.user.entity.QUser;
import com.nicasia.rfc.usermanagement.user.entity.User;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl extends BaseRepositoryImpl<User, UserRepository> implements UserRepositoryCustom {

    QUser user = QUser.user;

    public UserRepositoryImpl(Class<?> domainClass) {
        super(User.class);
    }

    @Lazy
    @Autowired
    public void setRepository(UserRepository userRepository) {
        this.repository = userRepository;
    }


    @Override
    public Optional<User> findByUserName(String userName) {
        return repository.findOne(user.username.eq(userName).and(user.status.eq(Status.ACTIVE)));
    }

    @Override
    public List<User> findAllUserBasedOnStatus(Status status) {
        return (List<User>) repository.findAll(user.status.eq(status));
    }

    @Override
    public List<User> searchUser(String userName) {

        BooleanBuilder where = new BooleanBuilder();
        if (userName != null && !userName.isEmpty()) {
            where.and(user.username.eq(userName));
        }
        where.and(user.status.eq(Status.ACTIVE));

        return (List<User>) repository.findAll(where);
    }
}
