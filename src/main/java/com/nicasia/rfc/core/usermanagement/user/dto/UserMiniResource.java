package com.nicasia.rfc.core.usermanagement.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserMiniResource {

    private String fullName;
    private String userName;
    private Long userId;
    private String departmentCode;
    private String designationCode;

}
