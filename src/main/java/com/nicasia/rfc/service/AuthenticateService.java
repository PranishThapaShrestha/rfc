package com.nicasia.rfc.service;

import com.nicasia.rfc.auth.LoginRequest;
import org.springframework.http.ResponseEntity;

public interface AuthenticateService {

    ResponseEntity<?> authenticateUser(LoginRequest loginRequest);



}
