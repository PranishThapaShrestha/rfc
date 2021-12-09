package com.nicasia.rfc.core.usersession.repo;

import com.nicasia.rfc.core.usersession.model.QUserSession;
import com.nicasia.rfc.core.usersession.model.UserSession;
import com.nicasia.rfc.shared.abstracts.BaseRepositoryImpl;

import java.util.List;

public class UserSessionRepositoryImpl extends BaseRepositoryImpl<UserSession, UserSessionRepository> implements UserSessionRepositoryCustom {

    QUserSession qUserSession = QUserSession.userSession;

    public UserSessionRepositoryImpl() {
        super(UserSession.class);
    }

    public void setRepository(UserSessionRepository userSessionRepository) {
        this.repository = userSessionRepository;
    }

    @Override
    public UserSession findLAstSessionByAdminId(Long id) {
        return from(qUserSession).where(qUserSession.user.id.eq(id)
                .and(qUserSession.logoutType.isNull()))
                .orderBy(qUserSession.createdAt.desc())
                .limit(1)
                .select(QUserSession.userSession).fetchOne();

    }

    @Override
    public List<UserSession> findLastUndoneLog(Long userId) {
        return (List<UserSession>) repository.findAll(qUserSession.user.id.eq(userId)
                .and(qUserSession.logoutType.isNull()));
    }
}
//
