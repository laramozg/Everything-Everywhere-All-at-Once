package com.example.db.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "login1")
    private User sender;  // Связь с таблицей users

    @ManyToOne
    @JoinColumn(name = "login2")
    private User receiver;  // Связь с таблицей users

    private Timestamp time;

    private String content;
}
