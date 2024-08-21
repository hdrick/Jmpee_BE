package com.hdrick.jumpee.controller;

import com.hdrick.jumpee.dto.AddressDTO;
import com.hdrick.jumpee.dto.UserCreationDTO;
import com.hdrick.jumpee.model.User;
import com.hdrick.jumpee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserCreationDTO userCreationDTO){
        User createdUser = userService.createUser(userCreationDTO);
        return ResponseEntity.ok(createdUser);
    }


    @PostMapping("/{userId}/addresses")
    public ResponseEntity<Void> addAddress(@PathVariable Long userId, @RequestBody AddressDTO addressDTO) {
        userService.addAddressToUser(userId, addressDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsersWithAddresses() {
        List<User> users = userService.getAllUsersWithAddresses();
        return ResponseEntity.ok(users);
    }

}
