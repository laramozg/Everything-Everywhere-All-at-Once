package com.example.db.controller;

import com.example.db.model.AuthRequest;
import com.example.db.model.UserRequest;
import com.example.db.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/reg")
    public ResponseEntity<?> registration(@RequestBody UserRequest userRequest) {
        return userService.registration(userRequest.getName(), userRequest.getSurname(), userRequest.getLogin(), userRequest.getPassword());
    }

    @PostMapping("/auth")
    public ResponseEntity<?> authorization(@RequestBody AuthRequest authRequest) {
        return userService.authorization(authRequest.getLogin(), authRequest.getPassword());
    }

}