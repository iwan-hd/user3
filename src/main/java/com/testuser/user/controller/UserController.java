package com.testuser.user.controller;

import com.testuser.user.model.PartNumber;
import com.testuser.user.model.User;
import com.testuser.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
