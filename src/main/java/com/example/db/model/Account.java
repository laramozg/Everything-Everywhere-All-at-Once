package com.example.db.model;
import lombok.*;

import jakarta.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account {
    @Id
    private String login;

    private String nickname;
    private Integer friends;

    @OneToOne
    @JoinColumn(name = "login")
    private User user;

}
