package com.nicasia.rfc.shared.succesresponse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SuccessResponse {

    private String successMessage;
}
