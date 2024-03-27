package com.cintelink.message.repository;


import com.cintelink.message.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("select u from message_user u where u.username = ?1")
    User selectUserByUserName(String userName);
}

