package com.nicasia.rfc.core.usersession.event;

import com.nicasia.rfc.core.usersession.dto.SessionEventResource;
import com.nicasia.rfc.core.usersession.dto.SessionEventType;
import org.springframework.context.ApplicationEvent;

public class SessionEvent extends ApplicationEvent {
    private static final long serialVersionUID = 1L;

    private SessionEventType sessionEventType;
    private SessionEventResource sessionEventResource;

    public SessionEvent(Object source, SessionEventType sessionEventType, SessionEventResource sessionEventResource) {
        super(source);
        this.sessionEventType = sessionEventType;
        this.sessionEventResource = sessionEventResource;
    }

    public SessionEventType getSessionEventType() {
        return sessionEventType;
    }

    public SessionEventResource getSessionEventResource() {
        return sessionEventResource;
    }

}
