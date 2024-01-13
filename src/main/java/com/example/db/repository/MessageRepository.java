package com.example.db.repository;

import com.example.db.model.Friends;
import com.example.db.model.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message,String> {

    @Query(value = "Select * FROM get_messages(:user1,:user2)", nativeQuery = true )
    List<MessageProjection> getAllMessageByUser(@Param("user1") String user1, @Param("user2") String user2);


}
