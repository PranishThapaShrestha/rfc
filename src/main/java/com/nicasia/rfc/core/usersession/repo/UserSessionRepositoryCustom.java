package com.nicasia.rfc.core.usersession.repo;

import com.nicasia.rfc.core.usersession.model.UserSession;
import com.nicasia.rfc.shared.abstracts.BaseRepositoryCustom;

import java.util.List;

public interface UserSessionRepositoryCustom extends BaseRepositoryCustom<UserSession> {

    UserSession findLAstSessionByAdminId(Long id);

    List<UserSession> findLastUndoneLog(Long userId);

}

