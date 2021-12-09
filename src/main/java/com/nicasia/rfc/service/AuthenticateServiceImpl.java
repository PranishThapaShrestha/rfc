package com.nicasia.rfc.service;

import com.nicasia.rfc.auth.LoginRequest;
import com.nicasia.rfc.security.jwt.JwtUtils;
import com.nicasia.rfc.security.jwt.UserDetailsServiceImpl;
import com.nicasia.rfc.core.usermanagement.user.entity.User;
import com.nicasia.rfc.core.usermanagement.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
@Slf4j
public class AuthenticateServiceImpl implements AuthenticateService {


    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserService userService;

    public AuthenticateServiceImpl(AuthenticationManager authenticationManager, JwtUtils jwtUtils, UserDetailsServiceImpl userDetailsService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userService = userService;
    }

    @Override
    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {

        byte[] decodedBytesUsername = Base64.getDecoder().decode(loginRequest.getUsername());
        String userName = new String(decodedBytesUsername);
        byte[] decodedBytesPassword = Base64.getDecoder().decode(loginRequest.getPassword());
        String password = new String(decodedBytesPassword);

        LoginRequest loginRequest1 = new LoginRequest();
        loginRequest1.setUsername(userName);
        loginRequest1.setPassword(password);
        log.info("Current username trying to login:{}", loginRequest1.getUsername());

        Authentication authentication = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(userName
                        , "12345"));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.generateToken(authentication);
        User user = userService.findByName(userName).orElse(null);


        return null;
    }
}
