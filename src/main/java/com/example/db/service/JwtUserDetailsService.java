package com.example.db.service;

import com.example.db.exceptions.IncorrectUserCredentialsException;
import com.example.db.jwt.JwtTokenProvider;
import com.example.db.model.AuthorizationData;
import com.example.db.model.User;
import com.example.db.repository.AuthorizationRepository;
import com.example.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class JwtUserDetailsService implements UserDetailsService {

    private final AuthorizationRepository authRepository;


    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<AuthorizationData> userOptional = authRepository.findById(username);

        if (!userOptional.isPresent()) {
            throw new IncorrectUserCredentialsException("Данный пользователь не найден! (не передавай елки-палки токен)");
        }
        return authRepository.findById(username).orElseThrow(NoSuchElementException::new);
    }
}