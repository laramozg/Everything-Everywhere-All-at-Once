package com.example.db.model;
import com.example.db.model.enums.StatusFriends;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "friends")
public class Friends {
    @Id
    @ManyToOne
    @JoinColumn(name = "login1")
    private User user1;

    @Id
    @ManyToOne
    @JoinColumn(name = "login2")
    private User user2;

    @Enumerated(EnumType.STRING)
    private StatusFriends status;
}
