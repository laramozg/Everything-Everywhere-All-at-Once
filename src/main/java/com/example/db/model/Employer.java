package com.example.db.model;
import lombok.*;
import jakarta.persistence.*;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employer")
public class Employer {
    @Id
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Id
    @ManyToOne
    @JoinColumn(name = "hero")
    private User hero;

    @Id
    @ManyToOne
    @JoinColumn(name = "coordinator")
    private User coordinator;
}
