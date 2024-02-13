package com.example.db.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmployerResponse {
    private Integer id;
    private String hero;
    private String coordinator;
}
