package com.example.db.controller;

import com.example.db.dto.NameRequest;
import com.example.db.service.TechnicalSpecialistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/work")
public class TechnicalSpecialistController {
    private final TechnicalSpecialistService technicalSpecialistService;
    @GetMapping("/tasks")
    public ResponseEntity<?> getTasks(){
        return technicalSpecialistService.getTasks();
    }

    @PostMapping("/tasks")
    public ResponseEntity<?> addTask(@RequestBody NameRequest name){
        return technicalSpecialistService.addTask(name.getName());
    }

    @DeleteMapping("/tasks")
    public ResponseEntity<?> deleteTask(@RequestBody NameRequest name){
        return technicalSpecialistService.deleteTask(name.getName());
    }
}
