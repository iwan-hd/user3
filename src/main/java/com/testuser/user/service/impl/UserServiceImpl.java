package com.testuser.user.service.impl;

import com.testuser.user.model.User;
import com.testuser.user.repository.UserRepository;
import com.testuser.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long Id) throws Exception{
        User user = userRepository.findById(Id).orElse(new User());
        return user;

    }
}
