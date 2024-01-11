package com.example.db.repository;

import com.example.db.model.Hero;
import org.springframework.data.repository.CrudRepository;

public interface HeroRepository extends CrudRepository<Hero,String> {
    Hero findByLogin(String login);
}
