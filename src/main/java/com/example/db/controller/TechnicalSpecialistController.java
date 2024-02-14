package com.example.db.controller;

import com.example.db.dto.IncidentRequest;
import com.example.db.dto.NameRequest;
import com.example.db.dto.OrderRequest;
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

    @CrossOrigin
    @GetMapping("/tasks")
    public ResponseEntity<?> getTasks(){
        return technicalSpecialistService.getTasks();
    }

    @CrossOrigin
    @PostMapping("/tasks")
    public ResponseEntity<?> addTask(@RequestBody NameRequest name){
        return technicalSpecialistService.addTask(name.getName());
    }

    @CrossOrigin
    @DeleteMapping("/tasks")
    public ResponseEntity<?> deleteTask(@RequestBody NameRequest name){
        return technicalSpecialistService.deleteTask(name.getName());
    }

    @CrossOrigin
    @GetMapping("/incidents")
    public ResponseEntity<?> getOngoingOrders(){
        return technicalSpecialistService.getOngoingOrders();
    }

    @CrossOrigin
    @PostMapping("/incidents/addIncidents")
    public ResponseEntity<?> addIncidents(@RequestBody IncidentRequest incidentRequest){
        return technicalSpecialistService.addIncidents(incidentRequest.getMonster(),incidentRequest.getNameUniversal(),incidentRequest.getLatitude(),incidentRequest.getLongitude());
    }

    @CrossOrigin
    @PostMapping("/incidents")
    public ResponseEntity<?> resultFinishDone(@RequestBody OrderRequest orderRequest){
        return technicalSpecialistService.resultFinishDone(orderRequest.getId(),orderRequest.getName());
    }

}
