package com.testuser.user.service;

import com.testuser.user.model.Gr;
import com.testuser.user.model.User;
import com.testuser.user.payload.auth.request.SignUpRequest;

import javax.validation.Valid;
import java.util.List;

public interface UserService {
    List<User> findAllUser();
    User findUserById(Long Id) throws Exception;
    void deleteUser(Long Id);
    User updateUser(Long Id, @Valid SignUpRequest user);
}
