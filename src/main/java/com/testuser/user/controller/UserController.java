package com.testuser.user.controller;

import com.testuser.user.model.Gr;
import com.testuser.user.model.PartNumber;
import com.testuser.user.model.User;
import com.testuser.user.payload.auth.request.SignUpRequest;
import com.testuser.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*",allowedHeaders= "*")

public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public HashMap<String,Object> getAllUser(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add( "Accept","application/json");
        httpHeaders.add("Content-Type", "application/json");
        List<User> user1 =userService.findAllUser();

        HashMap<String,Object> map = new HashMap();
        if(user1 == null){
            map.put("message","error");

        } else {
            map.put("message","success");
            map.put("status", 200);//200=OK
            map.put("datatabel", ResponseEntity.ok().headers(httpHeaders).body(user1));
        }
        return map;

    }
// Find by id User

@GetMapping("/user/{Id}")
@PreAuthorize("hasRole('ADMIN')")
public HashMap<String,Object>  getUserById(@PathVariable Long Id ) throws Exception {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("Accept", "application/json");
    httpHeaders.add("Content-Type", "application/json");
    User user1 = userService.findUserById(Id);
    HashMap<String , Object > map = new HashMap<>();
    if( user1 == null){
        map.put("message", "error");
    } else {
        map.put("message" ,"success");
        map.put("status", 200);//200=OK
        map.put("datatabel", ResponseEntity.ok().headers(httpHeaders).body(user1));
    }
    return map;

}

    @PutMapping("/user/{Id}")
    @PreAuthorize("hasRole('ADMIN')")
    public HashMap<String, Object>  updateGr(@PathVariable Long Id, @Valid @RequestBody SignUpRequest signUpRequest){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept", "application/json");
        httpHeaders.add("Content-Type", "application/json");
        User user1 = userService.updateUser(Id,signUpRequest);
        HashMap<String , Object > map = new HashMap<>();
        if(user1 == null) {
            map.put("message" , "Data Tidak Ditemukan");
        } else {
            map.put("message" , "success");
            map.put("status", 200);//200=OK
            map.put("datatabel", ResponseEntity.ok().headers(httpHeaders).body(user1));
        }

        return map;

    }


    @DeleteMapping("/user/{Id}")
    @PreAuthorize("hasRole('ADMIN')")
    public HashMap<String , Object> deleteGr(@PathVariable Long Id){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept", "application/json");
        httpHeaders.add("Content-Type", "application/json");
        userService.deleteUser(Id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("message" , "success");
        map.put("status", 200);//200=OK
        map.put("datatabel"  , "deleted successfully");
        return map;

    }






}
