package com.example.db.repository;

import com.example.db.dto.AbilityProjection;
import com.example.db.model.Ability;
import com.example.db.model.HeroAbility;
import com.example.db.model.HeroAbilityId;
import com.example.db.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HeroAbilityRepository extends CrudRepository<HeroAbility, HeroAbilityId> {
    @Query(value = "SELECT a.title, ha.mastery_percentage FROM hero_ability ha INNER JOIN ability a on ha.ability_id = a.id and ha.login = :person", nativeQuery = true)
    List<AbilityProjection> getAbility (@Param("person") String person);

//    @Query(value = "SELECT ha FROM hero_ability ha WHERE ha.login = :user and ha.ability_id = :ability ", nativeQuery = true)
//    HeroAbility findByAbilityAndUser(@Param("ability") Integer ability, @Param("user") String user);

    @Query("SELECT ha FROM HeroAbility ha WHERE ha.ability = :ability AND ha.user = :user")
    HeroAbility findByAbilityAndUser(@Param("ability") Ability ability, @Param("user") User user);

}
