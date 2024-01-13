package com.example.db.controller;


import com.example.db.dto.NameRequest;
import com.example.db.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<?> changeNickname(@RequestBody NameRequest name) {
        return accountService.changeNickname(name.getName());
    }

    @GetMapping("/myFriends")
    public ResponseEntity<?> getFriends(){
        return accountService.getFriends();
    }

    @DeleteMapping("/myFriends")
    public ResponseEntity<?> deleteFriends(@RequestBody NameRequest name){
        return accountService.deleteFriends(name.getName());
    }

    @GetMapping("/allPerson")
    public ResponseEntity<?> getNewFriends(){return accountService.getNewFriends();}

    @PostMapping("/allPerson")
    public ResponseEntity<?> sendFriendshipRequest(@RequestBody NameRequest name){
        return accountService.sendFriendshipRequest(name.getName());
    }

    @GetMapping("/requests")
    public ResponseEntity<?> requestFriendshipRequest(){
        return accountService.requestFriendshipRequest();
    }


    @PostMapping("/requests")
    public ResponseEntity<?> acceptFriendshipRequest(@RequestBody NameRequest name){
        return accountService.acceptFriendshipRequest(name.getName());
    }



}
