package com.example.db.repository;

import com.example.db.model.Universal;
import org.springframework.data.repository.CrudRepository;

public interface UniversalRepository extends CrudRepository<Universal,String> {
    Universal findByName(String name);
}
