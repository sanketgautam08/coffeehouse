package com.coffeehouse.demo_coffeehouse.gateway;

import com.coffeehouse.demo_coffeehouse.model.UserInfo;
import com.coffeehouse.demo_coffeehouse.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@Slf4j
public class UserController {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserInfo>> getAllUsers(){
        List<UserInfo> allUsers = userService.getUsers();
        return ResponseEntity.status(HttpStatus.CREATED).body(allUsers);
    }

    @GetMapping(path = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<List<UserInfo>>> getAllUsers(@PathVariable("id") int id){
        Optional<List<UserInfo>> allUsers = userService.getUserById(id);
        if(allUsers.isEmpty()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(allUsers);
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(allUsers);
    }

    @PostMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addUser(@RequestBody UserInfo userInfo){
        try{
            UserInfo newUser = userService.createUser(userInfo);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        }catch(Exception e){
            log.error("Error while creating user : ",e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("Errors", List.of(Map.of())));
        }
    }
}
