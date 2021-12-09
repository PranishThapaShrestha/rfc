package com.nicasia.rfc.core.usermanagement.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class UserResource {

    private String fullname;
    private String username;
    private Date created;
    private String department;
    private String designation;

}
