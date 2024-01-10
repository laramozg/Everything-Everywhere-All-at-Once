package com.example.db.model;
import lombok.*;
import jakarta.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hero")
public class Hero {
    @Id
    private String login;
    private Integer skill;

    @OneToOne
    @JoinColumn(name = "login")
    private User user;
}
