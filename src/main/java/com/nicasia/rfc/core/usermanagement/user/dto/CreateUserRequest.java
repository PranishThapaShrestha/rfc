package com.nicasia.rfc.core.usermanagement.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateUserRequest {

    private String name;
    private String username;
    private Long departmentId;
    private Long designationId;

}
