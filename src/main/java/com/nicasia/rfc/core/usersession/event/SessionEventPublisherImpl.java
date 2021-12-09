package com.nicasia.rfc.core.usersession.event;

import com.nicasia.rfc.core.usersession.dto.SessionEventResource;
import com.nicasia.rfc.core.usersession.dto.SessionEventType;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Component
public class SessionEventPublisherImpl implements ApplicationEventPublisherAware, SessionEventPublisher {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }



    @Override
    public void addSession(Long userId, String remoteAddress) {
        SessionEventResource sessionEventResource = new SessionEventResource();
        sessionEventResource.setUserId(userId);
        sessionEventResource.setIpAddress(remoteAddress);
        applicationEventPublisher.publishEvent(new SessionEvent(this, SessionEventType.LOGIN, sessionEventResource));
    }

    @Override
    public void removeSession(Long userId) {
        SessionEventResource sessionEventResource = new SessionEventResource();
        sessionEventResource.setUserId(userId);
        applicationEventPublisher.publishEvent(new SessionEvent(this, SessionEventType.LOGOUT, sessionEventResource));
    }


}
