package com.example.db.controller;


import com.example.db.dto.*;
import com.example.db.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class AccountController {
    private final AccountService accountService;

    @CrossOrigin
    @GetMapping
    public ResponseEntity<?> getProfileInformation(){
        return accountService.getProfileInformation();
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<?> changeNickname(@RequestBody NameRequest name) {
        return accountService.changeNickname(name.getName());
    }

    @CrossOrigin
    @GetMapping("/myFriends")
    public ResponseEntity<?> getFriends(){
        return accountService.getFriends();
    }

    @CrossOrigin
    @DeleteMapping("/myFriends")
    public ResponseEntity<?> deleteFriends(@RequestBody NameRequest name){
        return accountService.deleteFriends(name.getName());
    }
    @CrossOrigin
    @GetMapping("/allPerson")
    public ResponseEntity<?> getNewFriends(){return accountService.getNewFriends();}

    @CrossOrigin
    @PostMapping("/allPerson")
    public ResponseEntity<?> sendFriendshipRequest(@RequestBody NameRequest name){
        return accountService.sendFriendshipRequest(name.getName());
    }

    @CrossOrigin
    @GetMapping("/requests")
    public ResponseEntity<?> requestFriendshipRequest(){
        return accountService.requestFriendshipRequest();
    }

    @CrossOrigin
    @PostMapping("/requests")
    public ResponseEntity<?> acceptFriendshipRequest(@RequestBody NameRequest name){
        return accountService.acceptFriendshipRequest(name.getName());
    }

    @CrossOrigin
    @GetMapping("/messages")
    public ResponseEntity<?> getMessages( @RequestBody NameRequest name){
        return accountService.getMessages(name.getName());
   }

    @CrossOrigin
    @PostMapping("/messages")
    public ResponseEntity<?> sendMessage( @RequestBody MessageRequest messageRequest){
        return accountService.sendMessage(messageRequest.getName(), messageRequest.getText());
    }

    @CrossOrigin
    @GetMapping("/moving/task")
    public ResponseEntity<?> getAbstractTasks() {
        return accountService.getAbstractTasks();
    }

    @CrossOrigin
    @PostMapping("/moving/result")
    public ResponseEntity<?> resultMoving(@RequestBody NameRequest nameRequest) {
        return accountService.resultMoving(nameRequest.getName());
    }

    @CrossOrigin
    @GetMapping("/incidents")
    public ResponseEntity<?> getAllOrder(){
        return accountService.getAllOrder();
    }

    @CrossOrigin
    @PostMapping("/incidents")
    public ResponseEntity<?> reservationOrder(@RequestBody OrderRequest reservationOrderRequest){
        return accountService.reservationOrder(reservationOrderRequest.getId(),reservationOrderRequest.getName());
    }

    @CrossOrigin
    @GetMapping("/questionnaire")
    public ResponseEntity<?> getSkills(){
        return accountService.getSkills();
    }

    @CrossOrigin
    @PostMapping("/questionnaire")
    public ResponseEntity<?> saveSkills(@RequestBody List<HeroAbilityRequest> list){
        return accountService.saveSkills(list);
    }
}
