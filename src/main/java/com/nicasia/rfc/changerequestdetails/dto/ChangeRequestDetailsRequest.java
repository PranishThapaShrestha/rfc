package com.nicasia.rfc.changerequestdetails.dto;

import com.nicasia.rfc.changerequestdetails.entity.Priority;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChangeRequestDetailsRequest {

    private String description;
    private String justification;
    private Priority priority;


}
