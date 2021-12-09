package com.nicasia.rfc.core.usersession.event;

public interface SessionEventPublisher {

    void addSession(Long userId, String remoteAddress);

    void removeSession(Long userId);

}
