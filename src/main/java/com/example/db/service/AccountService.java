package com.example.db.service;

import com.example.db.dto.AccountResponse;
import com.example.db.model.Role;
import com.example.db.model.UserRole;
import com.example.db.model.enums.RoleName;
import com.example.db.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final HeroRepository heroRepository;
    private final RoleRepository roleRepository;

    public ResponseEntity<?> getProfileInformation(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String role = roleRepository.findByUser(userRepository.findByLogin(username).getLogin());
        if (Objects.equals(role, RoleName.Герой.toString())) {

            return new ResponseEntity<>(new AccountResponse(accountRepository.findByLogin(username).getNickname(),accountRepository.findByLogin(username).getFriends(),heroRepository.findByLogin(username).getSkill()), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(accountRepository.findByLogin(username), HttpStatus.OK);

    }

}
