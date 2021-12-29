package com.nicasia.rfc.core.usermanagement.user.repo;

import com.nicasia.rfc.core.usermanagement.user.entity.QUser;
import com.nicasia.rfc.core.usermanagement.user.entity.User;
import com.nicasia.rfc.shared.abstracts.BaseRepositoryImpl;
import com.nicasia.rfc.shared.enums.Status;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl extends BaseRepositoryImpl<User, UserRepository> implements UserRepositoryCustom {

    QUser quser = QUser.user;

    public UserRepositoryImpl() {

        super(User.class);
    }

    @Lazy
    @Autowired
    public void setRepository(UserRepository userRepository) {
        this.repository = userRepository;
    }


    @Override
    public Optional<User> findByUserName(String userName) {
        return repository.findOne(quser.username.eq(userName).and(quser.status.eq(Status.ACTIVE)));
    }

    @Override
    public List<User> findAllUserBasedOnStatus(Status status) {
        return (List<User>) repository.findAll(quser.status.eq(status));
    }

    @Override
    public List<User> searchUser(String userName) {

        BooleanBuilder where = new BooleanBuilder();
        if (userName != null && !userName.isEmpty()) {
            where.and(quser.username.eq(userName));
        }
        where.and(quser.status.eq(Status.ACTIVE));

        return (List<User>) repository.findAll(where);
    }

    @Override
    public List<User> findAllUserByIds(List<Long> userIds) {
        return (List<User>) repository.findAll(quser.id.in(userIds));
    }
}
