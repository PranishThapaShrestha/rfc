package com.nicasia.rfc.Security.security;

import com.nicasia.rfc.usermanagement.user.entity.User;
import com.nicasia.rfc.usermanagement.user.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.transaction.Transactional;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username).orElseThrow
                (() -> new UsernameNotFoundException("Username not found"));
        return UserPrincipal.build(user);
    }


    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow
                (() -> new UsernameNotFoundException("User not found"));
        return UserPrincipal.build(user);
    }
}
