package com.example.db.service;

import com.example.db.model.Quest;
import com.example.db.repository.QuestRepository;
import com.example.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class TechnicalSpecialistService {
    private final QuestRepository questRepository;
    private final UserRepository userRepository;

    public ResponseEntity<?> getTasks(){
        return new ResponseEntity<>(questRepository.findAllQuests(), HttpStatus.OK);
    }

    public ResponseEntity<?> addTask(String nameQuest){
        Quest newQuest = Quest.builder()
                .name(nameQuest)
                .user(userRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()))
                .build();
        questRepository.save(newQuest);
        return new ResponseEntity<>("Задание успешно добавлено!", HttpStatus.OK);
    }

    public ResponseEntity<?> deleteTask(String nameQuest){
        if (Objects.equals(questRepository.findByName(nameQuest).getUser().getLogin(), SecurityContextHolder.getContext().getAuthentication().getName()))
            questRepository.deleteByName(nameQuest);
        return new ResponseEntity<>("Задание успешно удалено!", HttpStatus.OK);

    }
}
