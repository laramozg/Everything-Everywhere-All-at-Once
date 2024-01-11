package com.example.db.service;

import com.example.db.exceptions.IncorrectUserCredentialsException;
import com.example.db.interaction.ResponseUser;
import com.example.db.jwt.JwtTokenProvider;
import com.example.db.model.AuthorizationData;
import com.example.db.model.Role;
import com.example.db.model.User;
import com.example.db.model.UserRole;
import com.example.db.model.enums.RoleName;
import com.example.db.repository.AuthorizationRepository;
import com.example.db.repository.RoleRepository;
import com.example.db.repository.UserRepository;
import com.example.db.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final AuthorizationRepository authRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final  JwtTokenProvider jwtTokenProvider;


    public ResponseEntity<?> registration (String name, String surname,String login, String password,String role) {
        userCredentialsValidation(login,password);

        User user = User.builder()
                .login(login)
                .name(name)
                .surname(surname)
                .build();

        AuthorizationData auth = AuthorizationData.builder()
                .username(login)
                .password(passwordEncoder.encode(password))
                .user(user)
                .build();


        user.setAuthorizationData(auth);
        if (authRepository.findById(auth.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Данное имя пользователя занято!");
        }

        userRepository.save(user);
        authRepository.save(auth);


        Role roleUser = roleRepository.findByName(RoleName.valueOf(role));

        UserRole userRole = UserRole.builder()
                .user(user)
                .role(roleUser)
                .build();
        userRoleRepository.save(userRole);

        return new ResponseEntity<>(new ResponseUser(HttpStatus.CREATED.value(),"Регистрация прошла успешно" ),HttpStatus.OK);
    }

    public ResponseEntity<?> authorization (String login, String password) {
        AuthorizationData user = authRepository.findByUsername(login);
        String token = jwtTokenProvider.createToken(login);
        if (user == null) {
            throw new IncorrectUserCredentialsException("Неправильный логин или пароль!");
        }
        if (passwordEncoder.matches(password,user.getPassword())) {
            return new ResponseEntity<>(new ResponseUser(HttpStatus.OK.value(), "Вы успешно авторизовались!",token),HttpStatus.OK);
        } else {
            throw new IncorrectUserCredentialsException("Неправильный логин или пароль!");

        }
    }
    private void userCredentialsValidation(String username, String password) {
        if (!(password.length() <= 20 && 6 <= password.length())) {
            throw new IncorrectUserCredentialsException("Длина логина минимум 6 и максимум 20!");
        }

        if (!(username.length() <= 20 && 6 <= username.length())) {
            throw new IncorrectUserCredentialsException("Длина пароля минимум 6 и максимум 20!");
        }
    }
}