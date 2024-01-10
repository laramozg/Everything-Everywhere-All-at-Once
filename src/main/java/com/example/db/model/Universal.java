package com.example.db.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import jakarta.persistence.*;
import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "universal")
public class Universal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer universal_id;
    @Max(100)
    @Min(-100)
    private float coordinateX;
    @Max(100)
    @Min(-100)
    private float coordinateY;
    private float distance;




}
