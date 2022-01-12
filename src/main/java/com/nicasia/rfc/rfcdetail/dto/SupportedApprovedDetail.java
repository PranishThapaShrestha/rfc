package com.nicasia.rfc.rfcdetail.dto;

import com.nicasia.rfc.core.usermanagement.user.dto.UserMiniResource;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SupportedApprovedDetail {
    private Long approveSupportId;
    private UserMiniResource userMiniResource;
    private String status;
    private String requestedFor;


}
