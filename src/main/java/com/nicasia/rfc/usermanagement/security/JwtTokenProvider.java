package com.nicasia.rfc.usermanagement.security;

import com.nicasia.rfc.usermanagement.security.util.PropertyValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JwtTokenProvider {

  @Value(PropertyValue.JWT_SECRET)
  private String jwtSecret;

  @Value(PropertyValue.JWT_EXPIRY_TIME_INMLIS)
  private int jwtExpirationInMs;

  public String generateToken(Authentication authentication){
    UserPrincipal userPrincipal= (UserPrincipal) authentication.getPrincipal();

    Date now=new Date();
    Date expiryDate=new Date(now.getTime()+jwtExpirationInMs);

    return null;

  }

}
