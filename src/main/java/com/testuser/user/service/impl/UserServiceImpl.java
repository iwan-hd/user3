package com.testuser.user.service.impl;

import com.testuser.user.model.ERole;
import com.testuser.user.model.Gr;
import com.testuser.user.model.Role;
import com.testuser.user.model.User;
import com.testuser.user.payload.auth.request.SignUpRequest;
import com.testuser.user.repository.RoleRepository;
import com.testuser.user.repository.UserRepository;
import com.testuser.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long Id) throws Exception{
        User user = userRepository.findById(Id).orElse(new User());
        return user;

    }

    @Override
    public void deleteUser(Long Id) {
        User user = userRepository.findById(Id).orElse(new User());
      //  userRepository.deleteUserId(Id);
        userRepository.delete(user);
    }

    @Override
    public User updateUser(Long Id, SignUpRequest signUpRequest) {
        User user1 = userRepository.findById(Id).get();
        user1.setNama(signUpRequest.getNama());
        user1.setUserName(signUpRequest.getUsername());
        user1.setCreatedAt(signUpRequest.getCreatedAt());
        user1.setCreatedBy(signUpRequest.getCreatedBy());
        user1.setFoto(signUpRequest.getFoto());


        if (signUpRequest.getPassword()  != null){
            user1.setPassword(encoder.encode(signUpRequest.getPassword()));
        }

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error : Role is not found"));
            roles.add(userRole);

        } else if ( strRoles.size() == 1) {

            if (strRoles.contains("admin")) {
                Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                        .orElseThrow(() -> new RuntimeException("Error : Role is not found"));
                roles.add(adminRole);
            } else if (strRoles.contains("user_pn")) {
                Role userpnRole = roleRepository.findByName(ERole.ROLE_USER_PN)
                        .orElseThrow(() -> new RuntimeException("Error : Role is not found"));
                roles.add(userpnRole);
            } else if (strRoles.contains("user_gr")) {
                Role usergrRole = roleRepository.findByName(ERole.ROLE_USER_GR)
                        .orElseThrow(() -> new RuntimeException("Error : Role is not found"));
                roles.add(usergrRole);
            }

        } else if (strRoles.size() > 1) {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error : admin Role is not found"));
                        roles.add(adminRole);

                        break;
                    case "user_pn":
                        Role userpnRole = roleRepository.findByName(ERole.ROLE_USER_PN)
                                .orElseThrow(() -> new RuntimeException("Error : userpn Role is not found"));
                        roles.add(userpnRole);
                        break;
                    case "user_gr":
                        Role usergrRole = roleRepository.findByName(ERole.ROLE_USER_GR)
                                .orElseThrow(() -> new RuntimeException("Error : usergr Role is not found"));
                        roles.add(usergrRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error : Role is not found"));
                        roles.add(userRole);

                }
            });
        }
        user1.setRoles(roles);
        User updateUser = userRepository.save(user1);
        return updateUser;
    }
}
