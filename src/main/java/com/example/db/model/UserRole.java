package com.example.db.model;

import lombok.*;

import jakarta.persistence.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@IdClass(UserRoleId.class)
@Entity
@Table(name = "userRole")
public class UserRole  {
    @Id
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    @Id
    @ManyToOne
    @JoinColumn(name = "login", referencedColumnName = "login")
    private User user;
}
