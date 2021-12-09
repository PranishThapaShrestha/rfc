package com.nicasia.rfc.core.usersession.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessionEventResource {

    private Long userId;
    private String token;
    private String ipAddress;

}
