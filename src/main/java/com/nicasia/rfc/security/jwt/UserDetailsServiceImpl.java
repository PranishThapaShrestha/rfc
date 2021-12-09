package com.nicasia.rfc.security.jwt;

import com.nicasia.rfc.core.usermanagement.user.entity.User;
import com.nicasia.rfc.core.usermanagement.user.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
@Service
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
