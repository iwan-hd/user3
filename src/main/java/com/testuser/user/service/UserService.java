package com.testuser.user.service;

import com.testuser.user.model.User;

import java.util.List;

public interface UserService {
    List<User> findAllUser();
    User findUserById(Long Id) throws Exception;

}
