package com.testuser.user.service.impl;

import com.testuser.user.model.User;
import com.testuser.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsersDetailServiceImp implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Data user " + username+  " tidak ditemukan"));

        return UserDetailsImp.build(user);
    }
}

