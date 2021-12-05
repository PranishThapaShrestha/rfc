package com.nicasia.rfc.succesresponse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SuccessResponse {

    private String successMessage;
}
