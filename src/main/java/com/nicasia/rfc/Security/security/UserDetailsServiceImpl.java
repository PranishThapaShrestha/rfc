package com.nicasia.rfc.Security.security;

import com.nicasia.rfc.usermanagement.user.entity.User;
import com.nicasia.rfc.usermanagement.user.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.transaction.Transactional;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public Userprincipal loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username).orElseThrow
                (() -> new UsernameNotFoundException("User not found as named"));

        return Userprincipal.build(user);
    }

    @Transactional
    public Userprincipal loadByUserId(Long id) throws UsernameNotFoundException {
        User user = userRepository.findById(id).orElseThrow
                (() -> new UsernameNotFoundException("User not found as Id"));


        return Userprincipal.build(user);
    }


}
