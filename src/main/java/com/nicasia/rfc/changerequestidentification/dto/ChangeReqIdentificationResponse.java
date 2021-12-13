package com.nicasia.rfc.changerequestidentification.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChangeReqIdentificationResponse {

    private Long changeRequest;
    private String changeTitle;
}

