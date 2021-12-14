package com.nicasia.rfc.changerequestdetails.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChangeRequestDetailsResponse {

    private Long id;
    private String description;
    private String justification;
    private String priority;
    private String status;

}
