package com.nicasia.rfc.core.usersession.event;

import com.nicasia.rfc.core.usermanagement.user.repo.UserRepository;
import com.nicasia.rfc.core.usersession.dto.SessionEventResource;
import com.nicasia.rfc.core.usersession.model.UserSession;
import com.nicasia.rfc.core.usersession.repo.UserSessionRepository;
import com.nicasia.rfc.shared.enums.LogoutType;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

    @Component
    public class SessionEventListener implements ApplicationListener<SessionEvent> {

        private final UserSessionRepository userSessionRepository;
        private final UserRepository userRepository;

        public SessionEventListener(UserSessionRepository userSessionRepository,
                                    UserRepository userRepository) {
            this.userSessionRepository = userSessionRepository;
            this.userRepository = userRepository;
        }


        public void onApplicationEvent(SessionEvent event) {
            switch (event.getSessionEventType()) {
                case LOGIN:
                    addLoginLog(event.getSessionEventResource());
                    break;
                case LOGOUT:
                    addLogoutLog(event.getSessionEventResource());
                    break;
                default:
            }
        }


        private void addLogoutLog(SessionEventResource sessionEventResource) {
            UserSession userSession = userSessionRepository.findLAstSessionByAdminId(sessionEventResource.getUserId());
            userSession.setLogoutType(LogoutType.USER);
            userSession.setLogoutTime(new Date());
            userSessionRepository.save(userSession);

        }

        private void completeLastLogin(SessionEventResource sessionEventResource) {
            List<UserSession> adminSessions = userSessionRepository.findLastUndoneLog(sessionEventResource.getUserId());
            if (adminSessions != null) {
                for (UserSession useression : adminSessions) {
                    useression.setLogoutType(LogoutType.SYSTEM);
                    useression.setLogoutTime(new Date(Date.from(useression.getCreatedAt().toInstant()).getTime() + 1000L * 60 * 20));
                }
                userSessionRepository.saveAll(adminSessions);
            }
        }


        private void addLoginLog(SessionEventResource sessionEventResource) {
            completeLastLogin(sessionEventResource);
            UserSession session = new UserSession();
            session.setUser(userRepository.findById(sessionEventResource.getUserId()).get());
            userSessionRepository.save(session);
        }



}
