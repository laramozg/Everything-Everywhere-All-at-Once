package com.example.db.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(columnDefinition = "string default nickname")
    private String nickname;
    @Column(columnDefinition = "integer default 0")
    private Integer friends;

    @OneToOne
    @JoinColumn(name = "login")
    @JsonIgnore
    private User user;

}
