package com.example.db.repository;

import com.example.db.model.Friends;
import com.example.db.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public interface FriendsRepository extends CrudRepository<Friends,String> {
    @Query(value = "SELECT * FROM get_friends(:person,:status)", nativeQuery = true)
    List<String> getFriends(@Param("person") String person,@Param("status") String status);

    @Query(value = "SELECT f.login1 FROM friends f WHERE f.status = :status and f.login2 = :person", nativeQuery = true)
    List<String> getFriendsRequest(@Param("person") String person,@Param("status") String status);

    @Query(value = "SELECT u.login FROM users u INNER JOIN user_role ur on u.login = ur.login inner join role r on ur.role_id = r.id WHERE u.login not IN (SELECT * FROM get_friends(:person,'REQUEST_ACCEPTED')) and u.login!= :person  and (r.name = 'Герой' or r.name = 'Координатор')", nativeQuery = true)
    List<String> getPerson(@Param("person") String person);


    @Query(value = "DELETE FROM friends f where f.login1 = :user1 and f.login2 = :user2 or f.login2 = :user1 and f.login1 = :user2 ", nativeQuery = true)
    @Transactional
    void deleteFriendsByName(@Param("user1") String user1, @Param("user2") String user2);

    @Query("SELECT f FROM Friends f WHERE f.user1 = :user1 and f.user2 = :user2 or f.user2 = :user1 and f.user1 = :user2")
    Friends findByUser1AndUser2(@Param("user1") String user1, @Param("user2") String user2);
}
