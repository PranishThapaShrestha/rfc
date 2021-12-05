package com.nicasia.rfc.usermanagement.designation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class DesignationResource {

    private Long id;
    private String name;
    private String code;
    private Date created;

}
