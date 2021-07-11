package com.testuser.user.controller;

import com.testuser.user.model.ERole;
import com.testuser.user.model.Role;
import com.testuser.user.model.User;
import com.testuser.user.payload.auth.request.LoginRequest;
import com.testuser.user.payload.auth.request.SignUpRequest;
import com.testuser.user.payload.auth.respone.JwtRespones;
import com.testuser.user.payload.auth.respone.MessageRespones;
import com.testuser.user.repository.RoleRepository;
import com.testuser.user.repository.UserRepository;
import com.testuser.user.security.jwt.JwtUtils;
import com.testuser.user.service.impl.UserDetailsImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(value = "*", maxAge = 3600)
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        //authenticate {username, password}
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        //update SecurityContext using Authenticate obj
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //generate JWT
        String jwt = jwtUtils.generateJwtToken(authentication);

        //get userDetails from Authenticate obj
        UserDetailsImp usersDetails = (UserDetailsImp) authentication.getPrincipal();

        //response contain JWT and userDetails data
        List<String> roles = usersDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                new JwtRespones(jwt,
                        usersDetails.getId(),
                        usersDetails.getUsername(),
                        usersDetails.getNama(),
                        usersDetails.getFoto(),
                        usersDetails.getCreatedAt(),
                        roles,
                        usersDetails.getCreatedBy()

                        ));
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        //cek exists email/username
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageRespones("Error : user already exists !"));
        }

//        if (userRepository.existsByEmail(signUpRequest.getEmail())){
//            return ResponseEntity.badRequest().body(new MessageRespones("Error : email already exists !"));
//        }

        //create user ( kalau gak roles default ROLE_USER)
        User user;
        user = new User(signUpRequest.getUsername(),
                signUpRequest.getNama(),
                signUpRequest.getFoto(),
                signUpRequest.getCreatedAt(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getCreatedBy()
                );
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

        //SAVE KE model USER pake methode save UserRepository
        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageRespones("Register user successfully !!"));
    }

}


