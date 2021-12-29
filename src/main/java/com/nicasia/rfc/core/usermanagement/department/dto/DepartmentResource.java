package com.nicasia.rfc.core.usermanagement.department.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class DepartmentResource {
    private Long id;
    private String name;
    private String code;
    private Date created;
    private String status;
}
