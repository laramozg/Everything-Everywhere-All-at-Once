package com.example.db.repository;

import com.example.db.model.Monster;
import org.springframework.data.repository.CrudRepository;

public interface MonsterRepository extends CrudRepository<Monster,String> {
    Monster findByName(String name);
}
