package com.example.db.service;

import com.example.db.dto.AccountResponse;
import com.example.db.dto.NameRequest;
import com.example.db.model.Friends;
import com.example.db.model.Message;
import com.example.db.model.enums.RoleName;
import com.example.db.model.enums.StatusFriends;
import com.example.db.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final HeroRepository heroRepository;
    private final RoleRepository roleRepository;
    private final FriendsRepository friendsRepository;
    private final MessageRepository messageRepository;

    public ResponseEntity<?> getProfileInformation(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String role = roleRepository.findByUser(userRepository.findByLogin(username).getLogin());
        if (Objects.equals(role, RoleName.Герой.toString())) {

            return new ResponseEntity<>(new AccountResponse(accountRepository.findByLogin(username).getNickname(),accountRepository.findByLogin(username).getFriends(),heroRepository.findByLogin(username).getSkill()), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(accountRepository.findByLogin(username), HttpStatus.OK);

    }
    public ResponseEntity<?> changeNickname(String newNick){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        accountRepository.findByLogin(username).setNickname(newNick);
        accountRepository.save(accountRepository.findByLogin(username));
        return new ResponseEntity<>("Никнейм успешно изменен!",HttpStatus.OK);
    }


    public ResponseEntity<?> getFriends(){
        return new ResponseEntity<>(friendsRepository.getFriends(SecurityContextHolder.getContext().getAuthentication().getName(),StatusFriends.REQUEST_ACCEPTED.toString()),HttpStatus.OK);
    }

    public ResponseEntity<?> deleteFriends(String name){
        friendsRepository.deleteFriendsByName(SecurityContextHolder.getContext().getAuthentication().getName(),name);
        return new ResponseEntity<>("Друг успешно удален!",HttpStatus.OK);
    }

    public ResponseEntity<?> getNewFriends(){
        return new ResponseEntity<>(friendsRepository.getPerson(SecurityContextHolder.getContext().getAuthentication().getName()),HttpStatus.OK);
    }

    public ResponseEntity<?> sendFriendshipRequest(String name){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Friends sendNewFriend = Friends.builder()
                .user1(userRepository.findByLogin(username))
                .user2(userRepository.findByLogin(name))
                .status(StatusFriends.REQUEST_SENT)
                .build();
        friendsRepository.save(sendNewFriend);
        return new ResponseEntity<>("Заявка в друзья отправлена!",HttpStatus.OK);
    }

    public ResponseEntity<?> requestFriendshipRequest(){
        return new ResponseEntity<>(friendsRepository.getFriendsRequest(SecurityContextHolder.getContext().getAuthentication().getName(),StatusFriends.REQUEST_SENT.toString()),HttpStatus.OK);
    }

    public ResponseEntity<?> acceptFriendshipRequest(String name){
        Friends friends = friendsRepository.findByUser1AndUser2(userRepository.findByLogin(name).getLogin(),userRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getLogin());
        friends.setStatus(StatusFriends.REQUEST_ACCEPTED);
        friendsRepository.save(friends);
        return new ResponseEntity<>("Пользователь добавлен в друзья!",HttpStatus.OK);
    }

    public ResponseEntity<?> getMessages(String name){
        List<MessageProjection> allMessage = messageRepository.getAllMessageByUser(userRepository.findByLogin(name).getLogin(),userRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getLogin());
        return new ResponseEntity<>(allMessage,HttpStatus.OK);
    }

    public ResponseEntity<?> sendMessage(String name, String text){
        Message newMessage = Message.builder()
                .sender(userRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()))
                .receiver(userRepository.findByLogin(name))
                .content(text)
                .time(getCurrentTimestampWithAutomaticDayAndMinute())
                .build();

        messageRepository.save(newMessage);
        return new ResponseEntity<>("Сообщение отправлено!",HttpStatus.OK);
    }



    public Timestamp getCurrentTimestampWithAutomaticDayAndMinute() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        int day = currentDateTime.getDayOfMonth();
        int minute = currentDateTime.getMinute();
        int hour = currentDateTime.getHour();
        int month = currentDateTime.getMonthValue();
        int year = currentDateTime.getYear();

        LocalDateTime customDateTime = LocalDateTime.of(year, month, day, hour, minute);

        return Timestamp.valueOf(customDateTime);
    }
}
