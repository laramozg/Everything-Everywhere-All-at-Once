package com.example.db.controller;

import com.example.db.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<?> getProfileInformation(){
        return accountService.getProfileInformation();
    }
}
