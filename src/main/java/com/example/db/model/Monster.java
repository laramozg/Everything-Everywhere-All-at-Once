package com.example.db.model;




import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "monsters")
public class Monster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Max(1000)
    @Min(1)
    private double price;
    @Max(10)
    @Min(1)
    private int level;
}
