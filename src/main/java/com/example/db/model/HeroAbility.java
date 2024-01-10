package com.example.db.model;
import lombok.*;
import jakarta.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "heroAbility")
public class HeroAbility {
    @Id
    @ManyToOne
    @JoinColumn(name = "login")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "ability_id")
    private Ability ability;

    @ManyToOne
    @JoinColumn(name = "universal_id")
    private Universal universal;
}
