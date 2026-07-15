package com.jai.user_service.controller;

import com.jai.user_service.dto.UserDto;
import com.jai.user_service.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service){
        this.service=service;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user){
        UserDto created=service.createUser(user);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
        UserDto user=service.getUserById(id);
        if(user==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id,@RequestBody UserDto dto){
        try{
            service.updateUser(id,dto);
            return ResponseEntity.ok("User updated successfully");
        } catch(IllegalArgumentException e){
            return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        try{
            service.deleteUser(id);
            return ResponseEntity.ok("User deleted successfully");
        } catch(IllegalArgumentException e){
            return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
        }
    }

}
